package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;
import java.util.List;
/**
 * @author mqxu
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_note",autoResultMap = true)
public class Note extends BaseEntity {
 private Long userId;
 private Long categoryId;
 private String title;
 private String content;
 @TableField(typeHandler = JacksonTypeHandler.class)
 private List<String> tags;
}