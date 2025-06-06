package top.dl.service;

import top.dl.dto.SceneDTO;
import top.dl.entity.Scene;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.BaseService;
import top.dl.query.SceneQuery;
import top.dl.vo.SceneVO;

/**
 * 场景服务接口
 * @author minder
 */
public interface SceneService extends BaseService<Scene> {
    /**
     * 分页查询场景列表
     */
    PageResult<SceneVO> page(SceneQuery query);

    /**
     * 保存场景（包含设备绑定）
     */
    void save(SceneDTO dto);

    /**
     * 更新场景（包含设备绑定）
     */
    void update(SceneDTO dto);

    /**
     * 根据ID获取场景详情（包含设备信息）
     */
    SceneVO getById(Long id);

    /**
     * 删除场景（同时删除设备关联）
     */
    void deleteScene(Long id);
}