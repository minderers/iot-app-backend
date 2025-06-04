package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Category;
import top.dl.framework.mybatis.dao.BaseDao;
/**
 * @author mqxu
 **/
@Mapper
public interface CategoryDao extends BaseDao<Category> {
}