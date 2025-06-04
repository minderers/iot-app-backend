package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;
/**
 * @author minder
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_device")
public class Device extends BaseEntity {
    private String deviceId;
    private String name;
    private Integer type;
    private Boolean status;
    private Float temperature;
    private Float humidity;
}