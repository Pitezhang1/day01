package com.jiyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiyun.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService extends IService<User> {
    User login(User user);
}
