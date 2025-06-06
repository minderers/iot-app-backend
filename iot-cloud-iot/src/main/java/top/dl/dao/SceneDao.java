package top.dl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.dl.entity.Scene;
import top.dl.framework.mybatis.dao.BaseDao;
import top.dl.vo.SceneVO;

import java.util.List;
import java.util.Map;

/**
 * 场景DAO
 * @author minder
 */
@Mapper
public interface SceneDao extends BaseDao<Scene> {
    /**
     * 获取场景列表
     */
    List<Scene> getList(Map<String, Object> params);

    /**
     * 获取场景详情（包含设备信息）
     */
    SceneVO getSceneWithDevices(@Param("sceneId") Long sceneId);

    /**
     * 获取场景列表（包含设备信息）
     */
    List<SceneVO> getSceneListWithDevices(Map<String, Object> params);
}