package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Message;
import top.dl.framework.mybatis.dao.BaseDao;

/**
 * @Author ctynt
 * @Date 2025/6/10
 * @Description MessageDao
 **/
@Mapper
public interface MessageDao extends BaseDao<Message> {
}
