package top.dl.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import top.dl.convert.DeviceConvert;
import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.feign.PackageService;
import top.dl.feign.UserService;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.common.utils.Result;
import top.dl.framework.security.utils.JwtUtil;
import top.dl.query.DeviceQuery;
import top.dl.service.DeviceService;
import top.dl.vo.DeviceVO;
import top.dl.vo.UserVO;

import java.util.List;
import java.util.Optional;

/**
 * @author: minder
 * @createTime: 2025/05/11 16:21
 * @description:
 **/
@RestController
@RequestMapping("api/device")
@Tag(name = "设备模块")
@AllArgsConstructor
@Slf4j
public class DeviceController {
    private final DeviceService deviceService;
    private final UserService userService;
    private final PackageService packageService;

    @PostMapping("/save")
    @Operation(summary = "新增设备")
    public Result<String> save(@Valid @RequestBody DeviceDTO deviceDto) {
        deviceService.save(DeviceConvert.INSTANCE.convert(deviceDto));
        return Result.ok();
    }

    @GetMapping("/list")
    @Operation(summary = "分页获取设备列表")
    public Result<PageResult<DeviceVO>> page(HttpServletRequest request,
                                             @ParameterObject @Valid DeviceQuery query) {

        String token = request.getHeader("Authorization");
        JSONObject claims = JwtUtil.getJSONObject(token);

        if (!claims.containsKey("userId")) {
            return Result.error("token 中缺少用户信息");
        }

        Integer userId = claims.getInt("userId");
        log.info("解析到的 userId: {}", userId);

        Result<UserVO> res = userService.getUserById(Long.valueOf(userId));
        UserVO userVO = res.getData();

        // 新增 debug 输出
        if (userVO == null) {
            log.warn("根据 userId={} 查询到的 userVO 为 null", userId);
            return Result.error("未找到用户信息");
        } else {
            log.info("userVO 内容: {}", userVO);
            Integer tenantId = userVO.getTenantId();
            if (tenantId == null) {
                log.warn("当前用户未绑定租户（tenantId == null）");
                return Result.error("当前用户未绑定租户");
            } else {
                log.info("当前用户所属租户ID为: {}", tenantId);
                query.setTenantId(Long.valueOf(tenantId));
                
                // 修改：直接使用tenantId作为t_package_user表中的user_id查询
                // 因为t_package_user表中的user_id实际上是租户ID
                Result<List<Integer>> deviceTypesResult = packageService.getDeviceTypesByTenantId(tenantId);
                if (deviceTypesResult.getCode() == 0 && deviceTypesResult.getData() != null && !deviceTypesResult.getData().isEmpty()) {
                    List<Integer> deviceTypes = deviceTypesResult.getData();
                    log.info("租户 {} 的套餐包含设备类型: {}", tenantId, deviceTypes);
                    
                    return Result.ok(deviceService.page(query, deviceTypes));
                } else {
                    log.warn("未找到租户 {} 对应的套餐设备类型信息", tenantId);
                    // 如果没有找到套餐信息，返回空列表
                    return Result.ok(new PageResult<>(null, 0));
                }
            }
        }
    }

    @GetMapping("/{deviceId}")
    @Operation(summary = "获取指定设备")
    public Result<DeviceVO> getDevice(@PathVariable String deviceId) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = deviceService.getOne(query);
        return Result.ok(DeviceConvert.INSTANCE.convert(device));
    }

    @PostMapping("/edit")
    @Operation(summary = "修改设备")
    public Result<String> update(@RequestBody @Valid DeviceDTO dto) {
        deviceService.update(dto);
        return Result.ok();
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "删除设备")
    public Result<String> delete(@PathVariable("id") Long id) {
        deviceService.removeById(id);
        return Result.ok();
    }
}