package com.org.tybar.controller;

import com.org.tybar.pojo.Email;
import com.org.tybar.service.EmailService;
import com.org.tybar.utils.CodeRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮件
     */
    @PostMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String sender, String receiver, String sendtitle, String sendcontent) throws UnsupportedEncodingException {
        Email email = new Email();
        email.setEmailFrom(sender);
        String sendTo = receiver;
        String title = sendtitle;
        String content = sendcontent;
        emailService.sendSimpleMail(sendTo,title,content);
        return "Sended!";
    }

    /**
     * 发送验证码
     */
    @GetMapping("/sendCode")
    @ResponseBody
    public String sendCode(@Validated String receiver) throws UnsupportedEncodingException {
        String code = new CodeRandom().codeRandom();
        String sendTo = receiver;
        String title = "验证码";
        String content = "您的验证码:"+ code +",有效期为3分钟";
        emailService.sendSimpleMail(sendTo,title,content);
        return code;
    }

}
