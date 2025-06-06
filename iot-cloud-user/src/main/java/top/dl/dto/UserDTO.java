package top.dl.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:18
 * @description:
 **/
@Data
@Schema(description = "⽤户信息dto")
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "⽤户名", required = true)
    private String username;
    @Schema(description = "密码", required = true)
    private String password;
    @Schema(description = "昵称", required = true)
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "性别 0：男 1：⼥ 2：未知", required = true)
    @Range(min = 0, max = 2, message = "性别不正确")
    private Integer gender;
    @Schema(description = "社区Id")
    private Integer communityId;
    @Schema(description = "租户Id")
    private Integer tenantId;
}
