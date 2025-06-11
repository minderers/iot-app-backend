package top.dl.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.dl.dao.DeviceDao;
import top.dl.entity.Device;
import top.dl.framework.common.exception.ServerException;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.CommandService;
import top.dl.service.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ctynt
 * @Date 2025/6/4
 * @Description CommandServiceImpl
 **/
@Service
@Slf4j
@AllArgsConstructor
public class CommandServiceImpl extends BaseServiceImpl<DeviceDao, Device> implements CommandService {

    private final MessageChannel mqttOutboundChannel;


    private final MessageService messageService;

    private static final Map<String, Long> heartbeatMap = new ConcurrentHashMap<>();


    @Override
    public void sendDeviceCommand(String deviceId, String command) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = this.getOne(query);
        if (device == null) {
            throw new ServerException("设备不存在");
        }
        // 构建JSON命令
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "device/" + deviceId + "/control")
                .build();
        mqttOutboundChannel.send(message);
    }

    @Override
    public void sendSceneCommand(String sceneId, String command) {
        Map<String, Object> map = new HashMap<>();
        map.put("sceneId", sceneId);
        map.put("command", command);
        String payload = JSON.toJSONString(map);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", "scene/" + sceneId + "/control")
                .build();
        mqttOutboundChannel.send(message);
    }

    @Scheduled(fixedRate = 10000) // 每分钟执行
    public void checkDeviceOffline() {
        long now = System.currentTimeMillis();
        long timeout = 5 * 1000; // 5秒

        System.out.println("检测心跳");

        heartbeatMap.forEach((deviceId, lastTime) -> {
            if (now - lastTime > timeout) {
                UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("deleted",0)
                        .eq("status",1)
                .set("status", 0);
                baseMapper.update(null, updateWrapper);
                log.info("设备 {} 心跳超时，标记为离线", deviceId);
            } else {
                log.info("心跳未超时");
            }
        });
    }


    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleStatusMessage(Message<?> message) {
        String payload = message.getPayload().toString();
        log.info("Received MQTT message headers: {}", message.getHeaders());
        String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
        assert topic != null;
        Boolean isMatch = topic.matches("device/.+/heartbeat");
        log.info("是否匹配:{}",isMatch);
        try {
            JSONObject json = JSON.parseObject(payload);
            log.info("MQTT 消息 - topic: {}, payload: {}", topic, json);

            if (topic.matches("device/.+/heartbeat")) {
                // 处理心跳消息
                Integer status = json.getInteger("status");
                String deviceId = json.getString("device_id");
                heartbeatMap.put(deviceId, System.currentTimeMillis());

                // 更新设备状态为在线，记录心跳时间
                UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("deleted",0).eq("status",0).set("status", 1); // 表示设备在线
                baseMapper.update(null, updateWrapper);

                log.info("收到设备 心跳，状态：{}", status);
                return;
            }

            // 如果是设备状态消息（如温湿度等）
            String deviceId = json.getString("device_id");
            Boolean isSwitched = json.getBoolean("isSwitched");

            String message_device_id = json.getString("message_device_id");
            String content = json.getString("content");
            String typeStr = json.getString("type");

            Float temperature = null;
            Float humidity = null;

            try {
                Double tempDouble = json.getDouble("temperature");
                if (tempDouble != null) {
                    temperature = tempDouble.floatValue();
                }

                Double humidDouble = json.getDouble("humidity");
                if (humidDouble != null) {
                    humidity = humidDouble.floatValue();
                }
            } catch (Exception e) {
                log.warn("解析温湿度异常", e);
            }

            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("device_id", deviceId)
                    .eq("deleted",0)
                    .set("temperature", temperature)
                    .set("humidity", humidity)
                    .set("is_switched", isSwitched);
            baseMapper.update(null, updateWrapper);

            log.info("设备状态更新: deviceId={},temperature={},humidity={},isSwitched={}", deviceId, temperature, humidity, isSwitched);

            // 处理内容消息
            if (StringUtils.isNotBlank(message_device_id) && StringUtils.isNotBlank(content)) {
                Integer type = Integer.parseInt(typeStr);
                top.dl.entity.Message msg = new top.dl.entity.Message();
                msg.setContent(content);
                msg.setType(type);
                msg.setDeviceId(message_device_id);
                messageService.save(msg);

                log.info("存储设备内容消息：deviceId={}, content={}, type={}", message_device_id, content, type);
            }

        } catch (Exception e) {
            log.error("处理 MQTT 消息异常: {}", e.getMessage(), e);
        }
    }

}
