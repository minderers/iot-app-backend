package top.dl.query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.common.query.Query;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:20
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "⽤户查询")
public class UserQuery extends Query {
    @Schema(description = "⽤户名")
    private String username;
    @Schema(description = "⼿机号")
    private String mobile;
}
