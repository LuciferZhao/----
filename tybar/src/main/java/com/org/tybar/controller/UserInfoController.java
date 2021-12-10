package com.org.tybar.controller;

import com.org.tybar.config.Result;
import com.org.tybar.pojo.Email;
import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.vo.SaveIndexPapers;
import com.org.tybar.pojo.vo.SaveUserInfo;
import com.org.tybar.pojo.UserInfo;
import com.org.tybar.service.EmailService;
import com.org.tybar.service.UserInfoService;
import com.org.tybar.utils.CodeRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tybar/user")
public class UserInfoController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private Email email;
    String code = null;
    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@Validated String email, @Validated String password){

        UserInfo userInfo = userInfoService.getUserInfo(email, password);
        if (userInfo != null){
            SaveUserInfo user = new SaveUserInfo(userInfo.getId(),userInfo.getUsername(),userInfo.getAvatar());
            return Result.success("success",user);
        }else{
            return Result.fail("error");
        }
    }

    /**
     * 判断邮箱是否已被注册
     */
    @GetMapping("/isRegist/{receiver}")
    @ResponseBody
    public Result isRegist(@PathVariable String receiver){
        if (userInfoService.findUserByEmail(receiver) == null){
            return Result.success("true");
        }else {
            return Result.fail("false");
        }
    }

    /**
     * 发送验证码
     */
    @GetMapping("/code")
    @ResponseBody
    public Result sendCode(@Validated String receiver) throws UnsupportedEncodingException {
            code = new CodeRandom().codeRandom();
            System.out.println(code);
            String sendTo = receiver;
            String title = "验证码";
            String content = "您的验证码:"+ code;
            emailService.sendSimpleMail(sendTo,title,content);
            return Result.success("success",code);
    }

    /**
     * 注册
     */
    @PostMapping("/regist")
    @ResponseBody
    public Result regist(
        @Validated String username,
        @Validated String password,
        @Validated String email,
        @Validated String usercode
    ){
        if (usercode.equals(code)){
            if (userInfoService.findUserByName(username) == null){
                UserInfo  userInfo = new UserInfo(username,password,email);
                userInfoService.addUser(userInfo);
                return Result.success("success");
            }else{
                return Result.fail("error");
            }
        }else {
            return Result.fail("验证码错误");
        }
    }


}

