package top.dl.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.entity.Share;
import top.dl.vo.ShareVO;

import java.util.List;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:26
 * @description:
 **/
@Mapper
public interface ShareConvert {
    ShareConvert INSTANCE = Mappers.getMapper(ShareConvert.class);

    ShareVO convert(Share entity);

    List<ShareVO> convertList(List<Share> list);
}
