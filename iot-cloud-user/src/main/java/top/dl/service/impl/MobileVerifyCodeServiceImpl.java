package top.dl.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.dl.framework.security.mobile.MobileVerifyCodeService;
import top.dl.sms.service.AliyunSmsService;
import top.dl.sms.service.RonglianyunSmsService;

/**
 * 短信验证码效验
 *
 * @author moqi
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
 private final AliyunSmsService smsService;
 @Override
 public boolean verifyCode(String mobile, String code) {
 return smsService.verifyCode(mobile, code);
 }
}