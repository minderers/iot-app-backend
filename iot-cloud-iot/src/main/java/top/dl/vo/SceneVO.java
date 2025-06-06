package top.dl.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.dl.framework.common.utils.DateUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 场景VO
 * @author minder
 */
@Data
@Schema(description = "场景VO")
public class SceneVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "场景ID")
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "场景名称")
    private String name;

    @Schema(description = "场景描述")
    private String description;

    @Schema(description = "场景类型")
    private String type;

    @Schema(description = "绑定的设备列表")
    private List<DeviceVO> devices;

    @Schema(description = "租户ID")
    private Long tenantId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime updateTime;
}