package top.dl.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/04/28 17:31
 * @description:
 **/
@Data
@Schema(description = "发布笔记Dto")
public class NoteDTO {
    @Schema(description = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;
    @Schema(description = "用户id")
    @NotBlank(message = "id不能为空")
    private Long userId;
    @Schema(description = "分类id")
    @NotBlank(message = "分类id不能为空")
    private Long categoryId;
    @Schema(description = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;
    @Schema(description = "标签")
    private List<String> tags;
}
