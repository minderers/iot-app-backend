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
@TableName("t_user")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String mobile;
    private String nickname;
    private String avatar;
    private String email;
    private String gender;
    private Integer status;
    private Integer communityId;
    private Integer tenantId;
    private String account;
}
