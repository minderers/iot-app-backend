package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;
/**
 * @author mqxu
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category extends BaseEntity {
 private String name;
 private String cover;
}