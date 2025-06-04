package top.dl.storage.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:06
 * @description:
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String endPoint;
    private String bucketName;
}
