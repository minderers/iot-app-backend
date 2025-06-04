package top.dl.framework.common.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author mqxu
 **/
@AutoConfiguration
@ConditionalOnClass(MinderStarterService.class)
@EnableConfigurationProperties(MinderStarterProperties.class)
@ConditionalOnProperty(prefix = "minder.starter", value = "enabled", havingValue = "true", matchIfMissing = true)
public class MinderStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean  //用户可自定义覆盖
    public MinderStarterService minderStarterService(MinderStarterProperties properties) {
        return new MinderStarterService(properties);
    }
}