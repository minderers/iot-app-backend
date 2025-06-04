package top.dl.vo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:17
 * @description:
 **/
@Data
@Schema(description = "⼿机号登录vo")
public class MobileLoginVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "⼿机号")
    private String mobile;
    @Schema(description = "accessToken")
    private String accessToken;
}
