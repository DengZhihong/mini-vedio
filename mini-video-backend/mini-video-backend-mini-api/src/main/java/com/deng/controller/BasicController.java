package com.deng.controller;

import cn.hutool.crypto.SecureUtil;
import com.deng.domain.Users;
import com.deng.service.IUserService;
import com.deng.utils.JSONResult;
import com.deng.utils.RedisOperator;
import com.deng.utils.ValidatorUtils;
import exception.MyInternalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user_redis_session";
}
