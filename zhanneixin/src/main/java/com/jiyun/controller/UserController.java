package com.jiyun.controller;

import com.jiyun.entity.User;
import com.jiyun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }
    @RequestMapping("update")
    public void updateUser(@RequestBody User user){
        userService.updateById(user);
    }
    @RequestMapping("/deleteOne")
    public void deleteOne(@RequestParam Integer id){
        userService.removeById(id);
    }
    @RequestMapping("/deleteBatch")
    public void deleteBatch(@RequestParam List<Integer> ids){
        userService.removeByIds(ids);
    }
    @RequestMapping("/findByid")
    public User findByid(@RequestParam Integer id){
        return userService.getById(id);
    }
    @RequestMapping("/findAll")
    public List<User> findAll(){
        return  userService.list();
    }
}
