package top.dl.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 场景DTO
 * @author minder
 */
@Data
@Schema(description = "场景DTO")
public class SceneDTO {
    @Schema(description = "场景ID")
    private Long id;

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "场景名称")
    @NotBlank(message = "场景名称不能为空")
    private String name;

    @Schema(description = "场景描述")
    private String description;

    @Schema(description = "场景类型")
    @NotBlank(message = "场景类型不能为空")
    private String type;

    @Schema(description = "绑定的设备ID列表（deviceId）")
    private List<String> deviceIds;

    @Schema(description = "租户ID")
    private Long tenantId;
}