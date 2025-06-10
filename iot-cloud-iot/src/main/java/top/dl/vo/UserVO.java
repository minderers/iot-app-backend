package top.dl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author: minder
 * @description: 用户信息VO
 **/
@Data
@Schema(description = "用户VO")
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "id")
    private Long id;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "姓名")
    private String nickname;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "头像")
    private String avatar;
    
    @Schema(description = "性别 0：男 1：女 2：未知")
    private Integer gender;
    
    @Schema(description = "租户ID")
    private Integer tenantId;
} 