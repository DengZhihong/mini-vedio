package com.deng.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deng.domain.Users;
import com.deng.mapper.UsersMapper;
import com.deng.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUsernameIsExist(String username) {
        QueryWrapper<Users> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", username);
        return usersMapper.selectOne(userWrapper) == null ? false : true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(Users user) {
        user.setId(IdUtil.simpleUUID());
        usersMapper.insert(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {
        QueryWrapper<Users> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", username);
        userWrapper.eq("password", password);
        return usersMapper.selectOne(userWrapper);
    }
}
