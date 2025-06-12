package top.dl.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.PackageDevice;
import top.dl.framework.mybatis.dao.BaseDao;

import java.util.List;

/**
 * @author: minder
 * @description: 套餐设备类型关联DAO
 **/
@Mapper
public interface PackageDeviceDao extends BaseDao<PackageDevice> {
    default List<PackageDevice> getByPackageId(Integer packageId) {
        return this.selectList(new QueryWrapper<PackageDevice>().eq("package_id", packageId).eq("deleted", 0));
    }
}