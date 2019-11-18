package com.deng.controller;

import com.deng.domain.Users;
import com.deng.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    UsersMapper mapper;

    @RequestMapping("/hello")
    public String hello(){
        Users user = Users.builder().username("xiaoma").faceImage("/asdas").fansCounts(11)
                .password("123456").receiveLikeCounts(1).id(UUID.randomUUID().toString()).build();
        mapper.insert(user);
        return "hello";
    }
}
