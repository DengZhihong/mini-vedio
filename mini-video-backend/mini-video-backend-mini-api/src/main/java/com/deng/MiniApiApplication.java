package com.deng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.deng.mapper")
public class MiniApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniApiApplication.class, args);
    }
}
