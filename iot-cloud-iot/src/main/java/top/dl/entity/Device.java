package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_device")
public class Device extends BaseEntity {
    private String deviceId;
    private String name;
    private Integer type;
    private Integer isSwitched;
    private Integer status;
    private Float temperature;
    private Float humidity;
    private Long tenantId;
    private Long adminId;
}