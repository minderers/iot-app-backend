package top.dl.sms.service;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import top.dl.framework.common.cache.RedisCache;
import top.dl.framework.common.cache.RedisKeys;
import top.dl.framework.common.exception.ErrorCode;
import top.dl.framework.common.exception.ServerException;
/**
 * @author mqxu
 **/
public abstract class SmsService {
 @Resource
 private RedisCache redisCache;
 abstract boolean sendSms(String mobile);
 public boolean verifyCode(String mobile, String code) {
 String captchaKey = RedisKeys.getCaptchaKey(mobile);
 String redisCode = (String) redisCache.get(captchaKey);
 // 校验验证码合法性
 if (ObjectUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
 throw new ServerException(ErrorCode.SMS_CODE_ERROR);
 }
 // 删除⽤过的验证码
 redisCache.delete(captchaKey);
 return true;
 }
}