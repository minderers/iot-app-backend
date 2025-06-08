package top.dl.service;

import top.dl.entity.Device;
import top.dl.framework.mybatis.service.BaseService;

/**
 * @Author ctynt
 * @Date 2025/6/4
 * @Description CommandService
 **/

public interface CommandService extends BaseService<Device> {
    void sendDeviceCommand(String deviceId, String command);
    void sendSceneCommand(String deviceId, String command);
}
