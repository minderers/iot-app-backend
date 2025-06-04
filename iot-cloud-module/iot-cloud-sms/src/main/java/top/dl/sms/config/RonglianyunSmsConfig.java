package top.dl.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties
;
import org.springframework.context.annotation.Configuration;
/**
 * @author minder
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "ronglianyun.sms")
public class RonglianyunSmsConfig {
 private String accountSId;
 private String accountToken;
 private String appId;
 private String serverIp;
 private String serverPort;
 private String templateId;
}