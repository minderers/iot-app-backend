package top.dl.framework.common.config;

/**
 * @author mqxu
 **/
public class MinderStarterService {
    private final MinderStarterProperties properties;

    public MinderStarterService(MinderStarterProperties properties) {
        this.properties = properties;
    }

    public String wrap(String message) {
        if (properties.isEnabled()) {
            return properties.getPrefix() + " - " + message;
        }
        return message;
    }
}