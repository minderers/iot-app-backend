package top.dl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import top.dl.dto.SceneDTO;
import top.dl.framework.common.utils.PageResult;
import top.dl.framework.common.utils.Result;
import top.dl.query.SceneQuery;
import top.dl.service.SceneService;
import top.dl.vo.SceneVO;

/**
 * 场景控制器
 * @author minder
 */
@RestController
@RequestMapping("api/scene")
@Tag(name = "场景模块")
@AllArgsConstructor
public class SceneController {
    
    private final SceneService sceneService;
    
    @PostMapping("/save")
    @Operation(summary = "新增场景")
    public Result<String> save(@Valid @RequestBody SceneDTO sceneDto) {
        sceneService.save(sceneDto);
        return Result.ok();
    }
    
    @GetMapping("/list")
    @Operation(summary = "分页获取场景列表")
    public Result<PageResult<SceneVO>> page(@ParameterObject @Valid SceneQuery query) {
        return Result.ok(sceneService.page(query));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "获取指定场景")
    public Result<SceneVO> getScene(@PathVariable Long id) {
        SceneVO scene = sceneService.getById(id);
        return Result.ok(scene);
    }
    
    @PostMapping("/edit")
    @Operation(summary = "修改场景")
    public Result<String> update(@RequestBody @Valid SceneDTO dto) {
        sceneService.update(dto);
        return Result.ok();
    }
    
    @PostMapping("/delete/{id}")
    @Operation(summary = "删除场景")
    public Result<String> delete(@PathVariable("id") Long id) {
        sceneService.removeById(id);
        return Result.ok();
    }
}