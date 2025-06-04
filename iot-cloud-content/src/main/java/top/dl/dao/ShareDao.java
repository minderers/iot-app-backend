package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Share;
import top.dl.framework.mybatis.dao.BaseDao;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:24
 * @description:
 **/
@Mapper
public interface ShareDao extends BaseDao<Share> {
}
