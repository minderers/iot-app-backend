package top.dl.framework.security.user;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * @author: minder
 * @createTime: 2025/04/25 9:09
 * @description:
 **/
public class SecurityUser {

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        UserDetail user = getUser();
        if (user == null) {
            return null;
        }

        return user.getId();
    }

}
