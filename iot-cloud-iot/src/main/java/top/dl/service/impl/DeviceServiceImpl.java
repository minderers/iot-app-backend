package top.dl.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.convert.DeviceConvert;
import top.dl.dao.DeviceDao;
import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.framework.common.constant.Constant;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.query.DeviceQuery;
import top.dl.service.DeviceService;
import top.dl.vo.DeviceVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author: minder
 * @createTime: 2025/05/11 16:19
 * @description:
 **/
@Service
@Slf4j
@AllArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDao, Device> implements DeviceService {

    @Override
    public PageResult<DeviceVO> page(DeviceQuery query) {
        Map<String, Object> params = getParams(query);
        IPage<Device> page = getPage(query);
        params.put(Constant.PAGE, page);
        List<Device> list = baseMapper.getList(params);
        return new PageResult<>(DeviceConvert.INSTANCE.convertList(list),
                page.getTotal());
    }
    private Map<String, Object> getParams(DeviceQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", query.getName());
        params.put("type", query.getType());
        params.put("status", query.getStatus());
        return params;
    }

    @Override
    public void update(DeviceDTO dto){
        Device device = baseMapper.selectById(dto.getId());
        if(device != null){
            device.setName(dto.getName());
            device.setType(dto.getType());
            device.setId(dto.getId());
            updateById(device);
        }
    }
}