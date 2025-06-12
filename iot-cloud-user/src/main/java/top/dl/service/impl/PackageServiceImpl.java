package top.dl.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import top.dl.dao.PackageDao;
import top.dl.dao.PackageUserDao;
import top.dl.dao.UserDao;
import top.dl.entity.Package;
import top.dl.entity.PackageUser;
import top.dl.entity.UserEntity;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.PackageService;

/**
 * @author: minder
 * @description: 套餐服务实现类
 **/
@Service
@AllArgsConstructor
@Slf4j
public class PackageServiceImpl extends BaseServiceImpl<PackageDao, Package> implements PackageService {
    private final PackageUserDao packageUserDao;
    private final UserDao userDao;

    @Override
    public Package getByUserId(Integer userId) {
        PackageUser packageUser = packageUserDao.getByUserId(userId);
        if (packageUser != null) {
            return baseMapper.selectById(packageUser.getPackageId());
        }
        return null;
    }

    @Override
    public Package getByTenantId(Integer tenantId) {
        // 直接使用tenantId作为t_package_user表中的user_id查询
        // 因为t_package_user表中的user_id实际上是租户ID
        PackageUser packageUser = packageUserDao.getByUserId(tenantId);
        if (packageUser != null) {
            Package pkg = baseMapper.selectById(packageUser.getPackageId());
            log.info("租户ID: {}, 查询到套餐: {}", tenantId, pkg);
            return pkg;
        }
        log.warn("租户ID: {}, 未查询到对应的套餐", tenantId);
        return null;
    }
}