package top.dl.vo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import java.io.Serial;
import java.io.Serializable;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:17
 * @description:
 **/
@Data
@Schema(description = "⽤户vo")
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "id")
    private Long id;
    @Schema(description = "⽤户名")
    private String username;
    @Schema(description = "姓名")
    private String nickname;
    @Schema(description = "⼿机号")
    private String mobile;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "性别 0：男 1：⼥ 2：未知")
    private Integer gender;
    @Schema(description = "社区Id")
    private Integer communityId;
    @Schema(description = "租户Id")
    private Integer tenantId;
}