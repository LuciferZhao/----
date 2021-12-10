package com.org.tybar.service;

import com.org.tybar.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

    // 查询所以用户
    List<UserInfo> getUserInfoList();

    //查询用户
    UserInfo getUserInfo(String email,String password);

    // 新增用户
    int addUser(UserInfo userInfo);

    //根据用户名查询用户
    UserInfo findUserByName(String username);

    //根据邮箱查询用户
    UserInfo findUserByEmail(String email);
}
