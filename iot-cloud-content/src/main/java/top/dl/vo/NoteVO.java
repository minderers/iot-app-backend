package top.dl.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.dl.framework.common.utils.DateUtils;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * @author minder
 */
@Data
@Schema(description = "笔记")
public class NoteVO implements Serializable {
 @Serial
 private static final long serialVersionUID = 1L;
 @Schema(description = "id")
 @NotBlank(message = "id不能为空")
 private Long id;
 @Schema(description = "userId")
 private Long userId;
 @Schema(description = "categoryId")
 private Long categoryId;
 @Schema(description = "标题")
 private String title;
 @Schema(description = "内容")
 private String content;
 @Schema(description = "标签")
 private List<String> tags;
 @Schema(description = "创建时间")
 @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
 private LocalDateTime createTime;
}