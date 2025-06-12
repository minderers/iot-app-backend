package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

/**
 * @author: minder
 * @description: 套餐设备类型关联实体类
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_package_device")
public class PackageDevice extends BaseEntity {
    private Integer packageId;
    private Integer deviceType;
}