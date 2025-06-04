package top.dl.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dl.convert.CategoryConvert;
import top.dl.entity.Category;
import top.dl.framework.common.utils.Result;
import top.dl.service.CategoryService;
import top.dl.vo.CategoryVO;
import java.util.List;
/**
 * @author mqxu
 **/
@RestController
@RequestMapping("api/category")
@Tag(name = "分类模块")
@AllArgsConstructor
public class CategoryController {
 private final CategoryService categoryService;
 @GetMapping("list")
 public Result<List<CategoryVO>> getCategoryList() {
 List<Category> categories = categoryService.list();
 List<CategoryVO> list = CategoryConvert.INSTANCE.convertList(categories);
 return Result.ok(list);
 }
}