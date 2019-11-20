package com.deng.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSON;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "用户相关业务的接口", tags = {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BasicController {

    @Value("${upload.file-space}")
    private String fileSpace;

    @Value("${upload.face}")
    private String facePath;

    @Autowired
    IUserService userService;

    @PostMapping("/{userId}/uploadFace")
    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "String", paramType = "path")
    public JSONResult uploadFace(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile[] files) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空");
        }
        //相对路径,保存到数据库
        String uploadDbPath = "/" + userId + facePath + "/";

        try {
            //1,判断上传文件是否为空
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传最终路径
                    String finalPath = fileSpace + uploadDbPath + fileName;
                    // 设置数据库保存的路径
                    uploadDbPath += fileName;
                    File file = new File(finalPath);
                    //如果父目录是文件或者不存在,先创建目录
                    if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
                        file.getParentFile().mkdirs();
                    }
                    files[0].transferTo(file);
                }
            } else {
                return JSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        }
        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadDbPath);
        userService.updateUserInfo(user);

        return JSONResult.ok(uploadDbPath);
    }


    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true,
            dataType = "String", paramType = "path")
    @GetMapping("/{userId}")
    public JSONResult query(@PathVariable("userId") String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空");
        }
        System.out.println("查询-------------------------------"+userId);
        Users user = userService.queryUserInfo(userId);
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user,userVO);
        return JSONResult.ok(userVO);
    }
}
