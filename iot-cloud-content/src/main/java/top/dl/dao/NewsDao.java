package top.dl.dao;
import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.News;
import top.dl.framework.mybatis.dao.BaseDao;

/**
 * @author: minder
 * @createTime: 2025/06/04 15:51
 * @description:
 **/
@Mapper
public interface NewsDao extends BaseDao<News> {
}
