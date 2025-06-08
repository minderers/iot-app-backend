package top.dl.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.dto.SceneDTO;
import top.dl.entity.Scene;
import top.dl.vo.SceneVO;

import java.util.List;

/**
 * 场景转换
 * @author minder
 */
@Mapper
public interface SceneConvert {
    SceneConvert INSTANCE = Mappers.getMapper(SceneConvert.class);
    
    Scene convert(SceneDTO sceneDto);
    
    SceneVO convert(Scene scene);
    
    List<SceneVO> convertList(List<Scene> list);
}