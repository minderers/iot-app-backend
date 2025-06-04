package top.dl.framework.common.cache;

/**
 * @author: minder
 * @createTime: 2025/04/25 8:47
 * @description:
 **/
public class RedisKeys {

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "api:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "api:token:" + accessToken;
    }

}
