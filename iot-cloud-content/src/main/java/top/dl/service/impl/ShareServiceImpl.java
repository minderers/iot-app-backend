package top.dl.service.impl;

import org.springframework.stereotype.Service;
import top.dl.dao.ShareDao;
import top.dl.entity.Share;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.ShareService;

/**
 * @author: minder
 * @createTime: 2025/04/25 9:25
 * @description:
 **/
@Service
public class ShareServiceImpl extends BaseServiceImpl<ShareDao, Share> implements ShareService {
}
