package top.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author ctynt
 * @Date 2025/6/10
 * @Description Message
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_message")
public class Message {
    private String deviceId;
    private String content;
    private Integer type;
}
