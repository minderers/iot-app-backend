package top.dl.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import top.dl.dao.DeviceDao;
import top.dl.entity.Device;
import top.dl.framework.common.exception.ServerException;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.CommandService;

import java.util.HashMap;
import java.util.Map;

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
    @Override
    public void sendCommand(String deviceId, String command) {
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

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleStatusMessage(Message<?> message) {
        String payload = message.getPayload().toString();
        try {
            JSONObject json = JSON.parseObject(payload);
            String deviceId = json.getString("device_id");
            Boolean status = json.getBoolean("status");
            Float temperature = json.getFloat("temperature");
            Float humidity = json.getFloat("humidity");
            // 更新数据库状态
            UpdateWrapper<Device> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("device_id", deviceId)
                    .set("status", status)
                    .set("temperature", temperature)
                    .set("humidity", humidity);
            baseMapper.update(null, updateWrapper);
            log.info("设备状态更新: {} -> {},{},{}", deviceId, status, temperature, humidity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
