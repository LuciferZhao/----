package com.org.tybar.service.impl;

import com.org.tybar.mapper.UserInfoMapper;
import com.org.tybar.pojo.UserInfo;
import com.org.tybar.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfoList() {
        return userInfoMapper.getUserInfoList();
    }

    @Override
    public UserInfo getUserInfo(String email, String password) {
        return userInfoMapper.getUserInfo(email,password);
    }

    @Override
    public int addUser(UserInfo userInfo) {
        return userInfoMapper.addUser(userInfo);
    }

    @Override
    public UserInfo findUserByName(String username) {
        return userInfoMapper.findUserByName(username);
    }

    @Override
    public UserInfo findUserByEmail(String email) {
        return userInfoMapper.findUserByEmail(email);
    }
}
