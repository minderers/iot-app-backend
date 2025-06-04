package top.dl.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dl.convert.ShareConvert;
import top.dl.entity.Share;
import top.dl.framework.common.utils.Result;
import top.dl.service.ShareService;
import top.dl.vo.ShareVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:27
 * @description:
 **/
@RestController
@RequestMapping("api/content")
@Tag(name = "内容模块")
@AllArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @GetMapping("shares")
    public Result<List<ShareVO>> getShareList() {
        List<Share> shares = shareService.list();
        List<ShareVO> list = ShareConvert.INSTANCE.convertList(shares);
        return Result.ok(list);
    }
}
