package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

/**
 * 场景设备关联实体类
 * @author minder
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_scene_device")
public class SceneDevice extends BaseEntity {
    /**
     * 场景ID
     */
    private Long sceneId;
    
    /**
     * 设备ID（对应设备表的deviceId字段）
     */
    private String deviceId;
    
    /**
     * 租户ID
     */
    private Long tenantId;
}