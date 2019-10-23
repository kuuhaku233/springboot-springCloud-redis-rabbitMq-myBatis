package com.userorder.OrderModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrderModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderModuleApplication.class,args);
    }
}
