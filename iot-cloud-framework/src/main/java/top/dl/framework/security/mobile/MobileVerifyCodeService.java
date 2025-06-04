package top.dl.framework.security.mobile;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:08
 * @description:
 **/
public interface MobileVerifyCodeService {

    boolean verifyCode(String mobile, String code);
}