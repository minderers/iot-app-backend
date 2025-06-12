package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

/**
 * @author: minder
 * @description: 套餐实体类
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_package")
public class Package extends BaseEntity {
    private String name;
}