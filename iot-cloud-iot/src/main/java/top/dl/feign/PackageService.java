package top.dl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.dl.framework.common.utils.Result;

import java.util.List;

/**
 * @author: minder
 * @description: 套餐服务Feign客户端
 **/
@FeignClient(name = "iot-cloud-user")
public interface PackageService {
    @GetMapping(value = "api/package/getDeviceTypesByTenantId")
    Result<List<Integer>> getDeviceTypesByTenantId(@RequestParam("tenantId") Integer tenantId);
}