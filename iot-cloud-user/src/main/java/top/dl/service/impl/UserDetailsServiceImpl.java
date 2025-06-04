package top.dl.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.dl.convert.UserConvert;
import top.dl.dao.UserDao;
import top.dl.entity.UserEntity;
import top.dl.service.MyUserDetailsService;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:25
 * @description:
 **/
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MyUserDetailsService myUserDetailsService;
    private final UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("⽤户名或密码错误");
        }
        return myUserDetailsService.getUserDetails(UserConvert.INSTANCE.convertDetail(userEntity));
    }
}
