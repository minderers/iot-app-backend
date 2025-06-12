package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Package;
import top.dl.framework.mybatis.dao.BaseDao;

/**
 * @author: minder
 * @description: 套餐DAO
 **/
@Mapper
public interface PackageDao extends BaseDao<Package> {
}