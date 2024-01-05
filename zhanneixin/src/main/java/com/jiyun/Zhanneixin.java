package com.jiyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiyun.mapper")
public class Zhanneixin {
    public static void main(String[] args) {
        SpringApplication.run(Zhanneixin.class, args);
        System.out.println("启动成功");
    }
}
