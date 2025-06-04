package top.dl.service;

import org.springframework.security.core.userdetails.UserDetails;
import top.dl.framework.security.user.UserDetail;
/**
 * @author mqxu
 **/
public interface MyUserDetailsService {
 UserDetails getUserDetails(UserDetail userDetail);
}