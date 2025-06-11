package top.dl.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.dl.entity.SceneDevice;
import top.dl.framework.mybatis.dao.BaseDao;

import java.util.List;

/**
 * 场景设备关联DAO
 * @author minder
 */
@Mapper
public interface SceneDeviceDao extends BaseDao<SceneDevice> {
    
    /**
     * 根据场景ID删除关联关系
     */
    default void deleteBySceneId(Long sceneId) {
        this.delete(new QueryWrapper<SceneDevice>().eq("scene_id", sceneId));
    }
    
    /**
     * 根据场景ID获取设备列表
     */
    default List<SceneDevice> getBySceneId(Long sceneId) {
        return this.selectList(new QueryWrapper<SceneDevice>().eq("scene_id", sceneId));
    }
    
    /**
     * 根据设备ID获取场景列表
     */
    default List<SceneDevice> getByDeviceId(String deviceId) {
        return this.selectList(new QueryWrapper<SceneDevice>().eq("device_id", deviceId));
    }
}