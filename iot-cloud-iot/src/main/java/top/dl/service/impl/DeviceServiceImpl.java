package top.dl.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.convert.DeviceConvert;
import top.dl.dao.DeviceDao;
import top.dl.dao.PackageDeviceDao;
import top.dl.dao.PackageUserDao;
import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.entity.PackageUser;
import top.dl.entity.PackageDevice;
import top.dl.framework.common.constant.Constant;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.query.DeviceQuery;
import top.dl.service.DeviceService;
import top.dl.vo.DeviceVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: minder
 * @createTime: 2025/05/11 16:19
 * @description:
 **/
@Service
@Slf4j
@AllArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<DeviceDao, Device> implements DeviceService {

    private final PackageUserDao packageUserDao;

    private final PackageDeviceDao packageDeviceDao;

    @Override
    public PageResult<DeviceVO> page(DeviceQuery query) {
        Map<String, Object> params = getParams(query);
        IPage<Device> page = getPage(query);
        params.put(Constant.PAGE, page);

        if (query.getTenantId() != null) {
            LambdaQueryWrapper<PackageUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(PackageUser::getUserId, query.getTenantId());
            PackageUser packageUser = packageUserDao.selectOne(wrapper);

            if (packageUser != null && packageUser.getPackageId() != null) {
                LambdaQueryWrapper<PackageDevice> deviceWrapper = new LambdaQueryWrapper<>();
                deviceWrapper.eq(PackageDevice::getPackageId, packageUser.getPackageId());

                List<PackageDevice> packageDevices = packageDeviceDao.selectList(deviceWrapper);

                List<Integer> deviceTypes = packageDevices.stream()
                        .map(PackageDevice::getDeviceType)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());

                if (!deviceTypes.isEmpty()) {
                    params.put("deviceTypes", deviceTypes);
                } else {
                    return new PageResult<>(Collections.emptyList(), 0);
                }
            } else {
                log.warn("未找到对应的 PackageUser 或 packageId 为空，tenantId: {}", query.getTenantId());
                return new PageResult<>(Collections.emptyList(), 0);
            }
        }

        List<Device> list = baseMapper.getList(params);
        return new PageResult<>(DeviceConvert.INSTANCE.convertList(list), page.getTotal());
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