package com.org.tybar.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Email {

    /**
     * 发件人邮箱
     */

    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
