package top.dl.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.dl.convert.UserConvert;
import top.dl.dto.AccountLoginDTO;
import top.dl.dto.MobileLoginDTO;
import top.dl.entity.UserEntity;
import top.dl.framework.common.exception.ServerException;
import top.dl.framework.security.cache.TokenStoreCache;
import top.dl.framework.security.mobile.MobileAuthenticationToken;
import top.dl.framework.security.user.UserDetail;
import top.dl.framework.security.utils.JwtUtil;
import top.dl.service.AuthService;
import top.dl.service.UserService;
import top.dl.vo.AccountLoginVO;
import top.dl.vo.MobileLoginVO;
import top.dl.vo.UserVO;

/**
 * @author: minder
 * @createTime: 2025/04/27 15:33
 * @description:
 **/
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenStoreCache tokenStoreCache;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AccountLoginVO loginByAccount(AccountLoginDTO login) {
        Authentication authentication;
        try {
            // ⽤户认证
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("⽤户名或密码错误");
        }
        // ⽤户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // ⽣成 accessToken
        String accessToken = JwtUtil.createToken(user.getId());
        // 保存⽤户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);
        AccountLoginVO accountLoginVO = new AccountLoginVO();
        accountLoginVO.setId(user.getId());
        accountLoginVO.setAccessToken(accessToken);
        accountLoginVO.setUsername(user.getUsername());
        return accountLoginVO;
    }
    @Override
    public MobileLoginVO loginByMobile(MobileLoginDTO login) {
        UserVO userVO = userService.getByMobile(login.getMobile());
        if (userVO == null) {
            UserEntity entity = UserConvert.INSTANCE.convert(login);
            entity.setUsername(login.getMobile());
            entity.setPassword(passwordEncoder.encode("123456"));
            entity.setNickname("新⽤户");
            entity.setAvatar("https://mqxu-oss.oss-cn-hangzhou.aliyuncs.com/avatar/1.jpg");
                    userService.save(entity);
        }
        Authentication authentication;
        try {
            // ⽤户认证
            authentication = authenticationManager.authenticate(new MobileAuthenticationToken(login.getMobile(), login.getCode()));
        } catch (BadCredentialsException e) {
            throw new ServerException("⼿机号或验证码错误");
        }
        // ⽤户信息
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // ⽣成 accessToken
        String accessToken = JwtUtil.createToken(userDetail.getId());
        // 保存⽤户信息到缓存
        tokenStoreCache.saveUser(accessToken, userDetail);
        MobileLoginVO mobileLoginVO = new MobileLoginVO();
        mobileLoginVO.setId(userDetail.getId());
        mobileLoginVO.setAccessToken(accessToken);
        mobileLoginVO.setMobile(login.getMobile());
        return mobileLoginVO;
    }
    @Override
    public void logout(String accessToken) {
        // 删除⽤户信息
        tokenStoreCache.deleteUser(accessToken);
    }
}
