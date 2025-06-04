package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.Device;
import top.dl.framework.mybatis.dao.BaseDao;
import java.util.List;
import java.util.Map;
/**
 * @author minder
 */
@Mapper
public interface DeviceDao extends BaseDao<Device> {
 List<Device> getList(Map<String, Object> params);
}