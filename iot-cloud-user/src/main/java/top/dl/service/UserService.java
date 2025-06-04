package top.dl.service;

import top.dl.dto.UserDTO;
import top.dl.entity.UserEntity;
import top.dl.framework.mybatis.service.BaseService;
import top.dl.vo.UserVO;

/**
 * @author: minder
 * @createTime: 2025/04/27 15:24
 * @description:
 **/
public interface UserService extends BaseService<UserEntity> {
    void save(UserDTO vo);
    void update(UserDTO dto);
    UserVO getByMobile(String mobile);
    UserVO getById(Long id);
}
