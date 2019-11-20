package com.deng.service;

import com.deng.domain.Users;

public interface IUserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return 存在:true,不存在false
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(Users user);

    /**
     * 通过用户名密码查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    Users queryUserForLogin(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(Users user);

    /**
     * @Description: 查询用户信息
     */
    Users queryUserInfo(String userId);
}
