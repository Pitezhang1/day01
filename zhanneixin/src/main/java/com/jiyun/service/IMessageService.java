package com.jiyun.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiyun.entity.Message;
import org.springframework.stereotype.Service;


public interface IMessageService extends IService<Message> {

    Page<Message> findPage(Page<Message> messagePage, Message message);

    void add(Message message);

    void updateReadStatus();


}
