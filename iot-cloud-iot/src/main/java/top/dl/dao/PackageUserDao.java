package top.dl.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.PackageUser;
import top.dl.framework.mybatis.dao.BaseDao;

/**
 * @author: minder
 * @description: 套餐用户关联DAO
 **/
@Mapper
public interface PackageUserDao extends BaseDao<PackageUser> {
}