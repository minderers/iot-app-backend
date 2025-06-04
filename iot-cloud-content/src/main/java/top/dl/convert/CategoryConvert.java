package top.dl.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.entity.Category;
import top.dl.vo.CategoryVO;
import java.util.List;
/**
 * @author mqxu
 **/
@Mapper
public interface CategoryConvert {
 CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);
 CategoryVO convert(Category entity);
 List<CategoryVO> convertList(List<Category> list);
}