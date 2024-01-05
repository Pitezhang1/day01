package com.jiyun.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiyun.entity.Message;
import com.jiyun.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired

    IMessageService messageService;

    @RequestMapping("/add")
    public void addMessage(@RequestBody Message message) {
        message.setNum(1);
        message.setReadStatus("未读");
        message.setStatus("发送成功！");
        messageService.add(message);
    }

    @RequestMapping("/update")
    public void updateMessage(@RequestBody Message message) {
        messageService.updateById(message);
    }
    @RequestMapping("/deleteOne")
    public void deleteOne(@RequestParam Integer id){
        messageService.removeById(id);
    }
    @RequestMapping("/deleteBatch")
    public void deletebatch(@RequestParam List<Integer> ids){
        messageService.removeByIds(ids);
    }
    @RequestMapping("/findById")
    public Message findById(@RequestParam Integer id){
        return messageService.getById(id);
    }
    @RequestMapping("/findAll")
    public List<Message> findAll(){
        return messageService.list();
    }
    @RequestMapping("/findPage")
    public Page<Message> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestBody Message message){
        Page<Message> messagePage=new Page<>(pageNum,pageSize);
        return messageService.findPage(messagePage,message);
    }
    @RequestMapping("updateReadStatus")
    public void updateReadStatus(){
        messageService.updateReadStatus();
    }

}















