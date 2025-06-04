package top.dl.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.vo.DeviceVO;
import java.util.List;
/**
 * 设备表
 *
 * @author minder
 */
@Mapper
public interface DeviceConvert {
 DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);
 Device convert(DeviceDTO deviceDto);
 DeviceVO convert(Device device);
 List<DeviceVO> convertList(List<Device> list);
}