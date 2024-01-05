package com.jiyun.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiyun.entity.Message;
import com.jiyun.mapper.MessageMapper;
import com.jiyun.mapper.UserMapper;
import com.jiyun.service.IMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("ALL")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    UserMapper userMapper;

    @Override
    public Page<Message> findPage(Page<Message> messagePage, Message message) {
        LambdaQueryWrapper<Message> qw = new LambdaQueryWrapper<>();
        qw.like(StringUtils.isNotBlank(message.getTitle()), Message::getTitle, message.getTitle());
        qw.like(StringUtils.isNotBlank(message.getContent()), Message::getContent, message.getContent());
        qw.eq(message.getSid() != null, Message::getSid, message.getSid());
        qw.eq(message.getFid() != null, Message::getFid, message.getFid());
        messageMapper.selectPage(messagePage, qw);
        for (Message m : messagePage.getRecords()) {
            m.setFname(userMapper.selectById(m.getFid()).getUsername());
            m.setSname(userMapper.selectById(m.getSid()).getUsername());
        }
        return messagePage;
    }

    @Override
    public void add(Message message) {
        messageMapper.insert(message);
        rabbitTemplate.convertAndSend("d16_exchange", "message_mq", JSON.toJSONString(message));

    }

    @Override
    public void updateReadStatus() {
        messageMapper.updateReadStatus();
    }
}
