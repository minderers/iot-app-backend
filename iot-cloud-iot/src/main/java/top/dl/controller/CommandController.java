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

    @PostMapping("/control")
    @Operation(summary = "发送控制命令")
    public Result<String> sendCommand(@RequestParam String deviceId, @RequestParam String command) {
        commandService.sendCommand(deviceId, command);
        System.out.print("fasongchengg");
        return Result.ok("指令发送成功！");
    }
}
