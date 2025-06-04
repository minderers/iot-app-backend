package top.dl.service;

import top.dl.dto.AccountLoginDTO;
import top.dl.dto.MobileLoginDTO;
import top.dl.vo.AccountLoginVO;
import top.dl.vo.MobileLoginVO;

/**
 * @author: minder
 * @createTime: 2025/04/27 15:23
 * @description:
 **/
public interface AuthService {
    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    AccountLoginVO loginByAccount(AccountLoginDTO login);
    /**
     * ⼿机短信登录
     *
     * @param login 登录信息
     */
    MobileLoginVO loginByMobile(MobileLoginDTO login);
    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}
