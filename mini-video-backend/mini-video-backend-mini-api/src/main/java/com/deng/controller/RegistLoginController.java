package com.deng.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.deng.domain.Users;
import com.deng.service.IUserService;
import com.deng.utils.JSONResult;
import com.deng.utils.ValidatorUtils;
import com.deng.vo.UserVO;
import exception.MyInternalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(value = "用户注册登录的接口", tags = {"注册和登录的controller"})
public class RegistLoginController extends BasicController {

    @Autowired
    IUserService userService;

    @PostMapping("/logout")
    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String",
            paramType = "query")
    public JSONResult logout(String userId) {
        redis.del(USER_REDIS_SESSION + ":" + userId);
        return JSONResult.ok();
    }

    @PostMapping("/regist")
    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    public JSONResult regist(@RequestBody Users user) {
        // 1. 判断用户名和密码必须不为空
        ValidatorUtils.validateEntity(user);
        // 2. 判断用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        // 3. 保存用户，注册信息
        if (!usernameIsExist) {
            user.setPassword(SecureUtil.md5(user.getPassword()));
            user.setFollowCounts(0);
            user.setNickname(user.getUsername());
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            userService.saveUser(user);
        } else {
            throw new MyInternalException("用户名已经存在");
        }
        user.setPassword("");

        UserVO userVO = setUserRedisSessionToken(user);

        return JSONResult.ok(userVO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    public JSONResult login(@RequestBody Users user) {
        // 1. 判断用户名和密码必须不为空
        ValidatorUtils.validateEntity(user);
        // 2. 判断用户名是否存在
        Users usersResult = userService.queryUserForLogin(user.getUsername(), SecureUtil.md5(user.getPassword()));
        //3. 返回
        if (usersResult != null) {
            usersResult.setPassword("");
            UserVO userVO = setUserRedisSessionToken(usersResult);
            return JSONResult.ok(userVO);
        } else {
            return JSONResult.errorMsg("用户名或密码不正确,请重试.");
        }
    }

    private UserVO setUserRedisSessionToken(Users userModel) {
        String uniqueToken = IdUtil.fastUUID();
        redis.set(USER_REDIS_SESSION + ":" + userModel.getId(), uniqueToken, 1000 * 60 * 30);
        UserVO userVO = new UserVO();
        userVO.setUserToken(uniqueToken);
        BeanUtil.copyProperties(userModel, userVO);
        return userVO;
    }
}
