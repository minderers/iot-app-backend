package top.dl.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import top.dl.convert.DeviceConvert;
import top.dl.dto.DeviceDTO;
import top.dl.entity.Device;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.common.utils.Result;
import top.dl.query.DeviceQuery;
import top.dl.service.DeviceService;
import top.dl.vo.DeviceVO;
/**
 * @author: minder
 * @createTime: 2025/05/11 16:21
 * @description:
 **/
@RestController
@RequestMapping("api/device")
@Tag(name = "设备模块")
@AllArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/save")
    @Operation(summary = "新增设备")
    public Result<String> save(@Valid @RequestBody DeviceDTO deviceDto) {
        deviceService.save(DeviceConvert.INSTANCE.convert(deviceDto));
        return Result.ok();
    }

    @GetMapping
    @Operation(summary = "分⻚获取设备列表")
    public Result<PageResult<DeviceVO>> page(@ParameterObject @Valid DeviceQuery query) {
        return Result.ok(deviceService.page(query));
    }

    @GetMapping("/{deviceId}")
    @Operation(summary = "获取指定设备")
    public Result<DeviceVO> getDevice(@PathVariable String deviceId) {
        QueryWrapper<Device> query = new QueryWrapper<>();
        query.eq("device_id", deviceId);
        Device device = deviceService.getOne(query);
        return Result.ok(DeviceConvert.INSTANCE.convert(device));
    }

    @PostMapping("/control")
    @Operation(summary = "发送控制命令")
    public Result<String> sendCommand(@RequestParam String deviceId, @RequestParam String command) {
        deviceService.sendCommand(deviceId, command);
        return Result.ok("指令发送成功！");
    }
}
