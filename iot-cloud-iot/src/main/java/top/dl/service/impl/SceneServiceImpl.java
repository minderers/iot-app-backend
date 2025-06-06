package top.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.dl.convert.SceneConvert;
import top.dl.dao.SceneDao;
import top.dl.dao.SceneDeviceDao;
import top.dl.dto.SceneDTO;
import top.dl.entity.Scene;
import top.dl.entity.SceneDevice;
import top.dl.framework.common.constant.Constant;
import top.dl.framework.common.exception.ServerException;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.query.SceneQuery;
import top.dl.service.SceneService;
import top.dl.vo.SceneVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 场景服务实现类
 * @author minder
 */
@Service
@Slf4j
@AllArgsConstructor
public class SceneServiceImpl extends BaseServiceImpl<SceneDao, Scene> implements SceneService {
    
    private final SceneDeviceDao sceneDeviceDao;
    
    @Override
    public PageResult<SceneVO> page(SceneQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        // 分页查询
        IPage<Scene> page = getPage(query);
        params.put(Constant.PAGE, page);
        // 数据列表（包含设备信息）
        List<SceneVO> list = baseMapper.getSceneListWithDevices(params);
        log.info("list:"+list);
        return new PageResult<>(list, page.getTotal());
    }
    
    private Map<String, Object> getParams(SceneQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", query.getName());
        params.put("userId", query.getUserId());
        params.put("tenantId", query.getTenantId());
        return params;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SceneDTO dto) {
        // 保存场景基本信息
        Scene scene = SceneConvert.INSTANCE.convert(dto);
        baseMapper.insert(scene);
        
        // 保存场景设备关联关系
        if (dto.getDeviceIds() != null && !dto.getDeviceIds().isEmpty()) {
            saveSceneDevices(scene.getId(), dto.getDeviceIds(), dto.getTenantId());
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SceneDTO dto) {
        Scene scene = baseMapper.selectById(dto.getId());
        if (scene == null) {
            throw new ServerException("场景不存在");
        }
        
        // 更新场景基本信息
        scene.setName(dto.getName());
        scene.setDescription(dto.getDescription());
        scene.setType(dto.getType());
        scene.setUserId(dto.getUserId());
        scene.setTenantId(dto.getTenantId());
        updateById(scene);
        
        // 更新场景设备关联关系
        // 先删除原有关联
        sceneDeviceDao.deleteBySceneId(dto.getId());
        // 再添加新关联
        if (dto.getDeviceIds() != null && !dto.getDeviceIds().isEmpty()) {
            saveSceneDevices(dto.getId(), dto.getDeviceIds(), dto.getTenantId());
        }
    }
    
    @Override
    public SceneVO getById(Long id) {
        return baseMapper.getSceneWithDevices(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteScene(Long id) {
        // 删除场景设备关联
        sceneDeviceDao.deleteBySceneId(id);
        // 删除场景
        removeById(id);
    }
    
    /**
     * 保存场景设备关联关系
     * @param sceneId 场景ID
     * @param deviceIds 设备ID列表（deviceId，String类型）
     * @param tenantId 租户ID
     */
    private void saveSceneDevices(Long sceneId, List<String> deviceIds, Long tenantId) {
        for (String deviceId : deviceIds) {
            SceneDevice sceneDevice = new SceneDevice();
            sceneDevice.setSceneId(sceneId);
            sceneDevice.setDeviceId(deviceId); // 这里是String类型的deviceId
            sceneDevice.setTenantId(tenantId);
            sceneDeviceDao.insert(sceneDevice);
        }
    }
}