package top.dl.service;

/**
 * @Author ctynt
 * @Date 2025/6/4
 * @Description CommandService
 **/

public interface CommandService {
    void sendCommand(String deviceId, String command);
}
