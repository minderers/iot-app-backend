package top.dl.service;

import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.BaseService;
import top.dl.query.DeviceQuery;
import top.dl.vo.DeviceVO;

public interface DeviceService extends BaseService<Device> {
 PageResult<DeviceVO> page(DeviceQuery query);
 void update(DeviceDTO device);

}