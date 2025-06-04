package top.dl.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:18
 * @description:
 **/
@Data
@Schema(description = "⼿机号登录")
public class MobileLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "⼿机号")
    private String mobile;
    @Schema(description = "验证码")
    private String code;
}
