package top.dl.service;

import top.dl.entity.Device;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.BaseService;
import top.dl.query.DeviceQuery;
import top.dl.vo.DeviceVO;
/**
 * @author mqxu
 **/
public interface DeviceService extends BaseService<Device> {
 PageResult<DeviceVO> page(DeviceQuery query);
 /**
 * 发送命令
 *
 * @param deviceId 设备id
 * @param command 命令
 */
 void sendCommand(String deviceId, String command);
}