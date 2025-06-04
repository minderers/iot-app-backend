package top.dl.framework.security.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author: minder
 * @createTime: 2025/04/25 9:09
 * @description:
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "dl.security")
public class SecurityProperties {
    /**
     * accessToken 过期时间(单位：秒)，默认2小时
     */
    private int accessTokenExpire = 60 * 60 * 2;
    /**
     * refreshToken 过期时间(单位：秒)，默认14天
     */
    private int refreshTokenExpire = 60 * 60 * 24 * 14;
}
