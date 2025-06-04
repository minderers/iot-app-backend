package top.dl.service.impl;
import org.springframework.stereotype.Service;
import top.dl.dao.CategoryDao;
import top.dl.entity.Category;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.CategoryService;
/**
 * @author: minder
 * @createTime: 2025/04/27 16:14
 * @description:
 **/
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDao, Category> implements CategoryService {
}
