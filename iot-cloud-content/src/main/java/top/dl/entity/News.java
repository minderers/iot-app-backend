package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.dl.framework.mybatis.entity.BaseEntity;

/**
 * @author: minder
 * @createTime: 2025/06/04 15:46
 * @description:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_news",autoResultMap = true)
public class News extends BaseEntity {
    private String title;
    private String content;
    private String cover;
    private Long tenantId;
}
