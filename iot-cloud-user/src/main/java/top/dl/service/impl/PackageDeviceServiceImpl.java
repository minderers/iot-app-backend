package top.dl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dl.dao.PackageDeviceDao;
import top.dl.entity.PackageDevice;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.PackageDeviceService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: minder
 * @description: 套餐设备类型服务实现类
 **/
@Service
@AllArgsConstructor
public class PackageDeviceServiceImpl extends BaseServiceImpl<PackageDeviceDao, PackageDevice> implements PackageDeviceService {
    @Override
    public List<Integer> getDeviceTypesByPackageId(Integer packageId) {
        List<PackageDevice> packageDevices = baseMapper.getByPackageId(packageId);
        return packageDevices.stream().map(PackageDevice::getDeviceType).collect(Collectors.toList());
    }
}