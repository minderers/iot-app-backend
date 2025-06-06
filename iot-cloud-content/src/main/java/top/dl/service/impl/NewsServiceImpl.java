package top.dl.service.impl;

import org.springframework.stereotype.Service;
import top.dl.dao.NewsDao;
import top.dl.entity.News;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.NewsService;

/**
 * @author: minder
 * @createTime: 2025/06/04 15:51
 * @description:
 **/
@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsDao, News> implements NewsService {
}
