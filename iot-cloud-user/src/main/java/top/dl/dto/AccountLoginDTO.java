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
@Schema(description = "账号登录")
public class AccountLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "⽤户名")
    private String username;
    @Schema(description = "密码")
    private String password;
}
