package top.dl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.dl.framework.common.utils.Result;
import top.dl.vo.UserVO;

/**
 * @author: minder
 * @createTime: 2025/04/28 15:33
 * @description:
 **/
@FeignClient(name = "iot-cloud-user")
public interface UserService {
    @GetMapping(value = "api/user/getUserById")
    Result<UserVO> getUserById(@RequestParam("id") Long id);
}
