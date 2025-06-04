package top.dl.framework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mqxu
 **/
@ConfigurationProperties(prefix = "minder.starter")
public class MinderStarterProperties {
    private String prefix = "DefaultPrefix";
    private boolean enabled = true;

    // getters and setters
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}