package top.dl.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.dl.framework.common.utils.Result;
import top.dl.service.CommandService;

/**
 * @Author ctynt
 * @Date 2025/6/4
 * @Description CommandController
 **/

public class CommandController {
    private CommandService commandService;

    @PostMapping("/control")
    @Operation(summary = "发送控制命令")
    public Result<String> sendCommand(@RequestParam String deviceId, @RequestParam String command) {
        commandService.sendCommand(deviceId, command);
        return Result.ok("指令发送成功！");
    }
}
