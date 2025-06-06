package top.dl.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dl.entity.News;
import top.dl.framework.common.utils.Result;
import top.dl.service.NewsService;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/06/04 15:53
 * @description:
 **/
@RestController
@RequestMapping("api/news")
@Tag(name = "资讯模块")
@AllArgsConstructor
public class NewsController {
    private final NewsService newsService;
    @GetMapping("list")
    public Result<List<News>> list() {
        List<News> list = newsService.list();
        return Result.ok(list);
    }
}
