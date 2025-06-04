package top.dl.convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.dl.dto.MobileLoginDTO;
import top.dl.dto.UserDTO;
import top.dl.entity.UserEntity;
import top.dl.framework.security.user.UserDetail;
import top.dl.vo.UserVO;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:19
 * @description:
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    UserVO convert(UserEntity entity);
    UserEntity convert(UserDTO dto);
    UserVO convert(UserDetail userDetail);
    UserEntity convert(MobileLoginDTO dto);
    UserDetail convertDetail(UserEntity entity);
}
