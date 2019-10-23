package com.userorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.userorder.mapper")
public class UserModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserModuleApplication.class,args);
    }
}
