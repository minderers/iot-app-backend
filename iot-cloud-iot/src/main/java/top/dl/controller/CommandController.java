package top.dl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dl.framework.common.utils.Result;
import top.dl.service.CommandService;

/**
 * @Author ctynt
 * @Date 2025/6/4
 * @Description CommandController
 **/

@RestController
@RequestMapping("api/command")
@Tag(name = "命令模块")
@AllArgsConstructor
public class CommandController {
    private final CommandService commandService;

    @PostMapping("/controlDevice")
    @Operation(summary = "发送控制设备命令")
    public Result<String> controlDevice(@RequestParam String deviceId, @RequestParam String command) {
        commandService.sendDeviceCommand(deviceId, command);
        return Result.ok("指令发送成功！");
    }
    @PostMapping("/controlScene")
    @Operation(summary = "发送控制场景命令")
    public Result<String> sendSceneCommand(@RequestParam String sceneId, @RequestParam String command) {
        commandService.sendSceneCommand(sceneId, command);
        return Result.ok("指令发送成功！");
    }

}
