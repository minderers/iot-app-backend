package top.dl.sms.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSmsConfig {
 private String accessKey;
 private String accessKeySecret;
 private String signName;
 private String templateCode;
}