package top.dl.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.dl.dao.MessageDao;
import top.dl.entity.Message;
import top.dl.framework.mybatis.service.impl.BaseServiceImpl;
import top.dl.service.MessageService;

/**
 * @Author ctynt
 * @Date 2025/6/10
 * @Description MessageServiceImpl
 **/
@Service
@Slf4j
@AllArgsConstructor
public class MessageServiceImpl extends BaseServiceImpl<MessageDao, Message> implements MessageService {
}
