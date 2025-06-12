package top.dl.service;

import top.dl.entity.PackageDevice;
import top.dl.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * @author: minder
 * @description: 套餐设备类型服务接口
 **/
public interface PackageDeviceService extends BaseService<PackageDevice> {
    List<Integer> getDeviceTypesByPackageId(Integer packageId);
}