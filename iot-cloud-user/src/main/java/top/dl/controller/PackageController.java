package top.dl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dl.entity.Package;
import top.dl.framework.common.utils.Result;
import top.dl.service.PackageDeviceService;
import top.dl.service.PackageService;

import java.util.List;

/**
 * @author: minder
 * @description: 套餐控制器
 **/
@RestController
@RequestMapping("api/package")
@Tag(name = "套餐模块")
@AllArgsConstructor
@Slf4j
public class PackageController {
    private final PackageService packageService;
    private final PackageDeviceService packageDeviceService;

    @GetMapping("/getByTenantId")
    @Operation(summary = "根据租户ID获取套餐信息")
    public Result<Package> getByTenantId(@RequestParam("tenantId") Integer tenantId) {
        return Result.ok(packageService.getByTenantId(tenantId));
    }

    @GetMapping("/getDeviceTypesByTenantId")
    @Operation(summary = "根据租户ID获取设备类型列表")
    public Result<List<Integer>> getDeviceTypesByTenantId(@RequestParam("tenantId") Integer tenantId) {
        log.info("接收到查询租户ID: {} 的设备类型请求", tenantId);
        Package pkg = packageService.getByTenantId(tenantId);
        if (pkg != null) {
            List<Integer> deviceTypes = packageDeviceService.getDeviceTypesByPackageId(pkg.getId().intValue());
            log.info("租户ID: {}, 套餐ID: {}, 设备类型列表: {}", tenantId, pkg.getId(), deviceTypes);
            return Result.ok(deviceTypes);
        }
        log.warn("未找到租户ID: {} 对应的套餐信息", tenantId);
        return Result.error("未找到租户对应的套餐信息");
    }
}