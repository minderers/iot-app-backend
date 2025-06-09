package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

/**
 * 场景实体类
 * @author minder
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_scene")
public class Scene extends BaseEntity {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 场景描述
     */
    private String description;

    /**
     * 场景类型
     */
    private String type;

    /**
     * 租户ID
     */
    private Long tenantId;
}