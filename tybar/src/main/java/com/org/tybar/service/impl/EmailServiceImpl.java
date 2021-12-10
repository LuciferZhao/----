package com.org.tybar.service.impl;

import com.org.tybar.pojo.Email;
import com.org.tybar.service.EmailService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Email email;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     * @param sendTo
     * @param title
     * @param content
     */
    public void sendSimpleMail(String sendTo, String title, String content) throws UnsupportedEncodingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(String.valueOf(new InternetAddress(email.getEmailFrom(),"施瓦锋格")));
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送带附件的简单邮件
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     */
    public void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {

    }

    /**
     * 发送模板邮件
     * @param sendTo
     * @param titel
     * @param content
     * @param attachments
     */
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments) {

    }
}
