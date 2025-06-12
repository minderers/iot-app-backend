package top.dl.service;

import top.dl.entity.Package;
import top.dl.framework.mybatis.service.BaseService;

/**
 * @author: minder
 * @description: 套餐服务接口
 **/
public interface PackageService extends BaseService<Package> {
    Package getByUserId(Integer userId);
    Package getByTenantId(Integer tenantId);
}