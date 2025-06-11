package top.dl.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.common.query.Query;

/**
 * 场景查询
 * @author minder
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "场景查询")
public class SceneQuery extends Query {
    @Schema(description = "场景名称")
    private String name;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "租户ID")
    private Long tenantId;
}