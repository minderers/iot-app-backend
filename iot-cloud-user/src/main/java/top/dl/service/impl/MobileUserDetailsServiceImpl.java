package top.dl.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.dl.convert.UserConvert;
import top.dl.dao.UserDao;
import top.dl.entity.UserEntity;
import top.dl.framework.security.mobile.MobileUserDetailsService;
import top.dl.service.MyUserDetailsService;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:26
 * @description:
 **/
@Service
@AllArgsConstructor
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    private final MyUserDetailsService myUserDetailsService;
    private final UserDao sysUserDao;
    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserEntity userEntity = sysUserDao.getByMobile(mobile);
        if (userEntity == null) {
            throw new UsernameNotFoundException("⼿机号或验证码错误");
        }
        return myUserDetailsService.getUserDetails(UserConvert.INSTANCE.convertDetail(userEntity));
    }
}
