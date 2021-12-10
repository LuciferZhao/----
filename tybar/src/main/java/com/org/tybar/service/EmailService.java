package com.org.tybar.service;

import javafx.util.Pair;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface EmailService {

    /**
     * 发送简单邮件
     * @param sendTo
     * @param title
     * @param content
     */
    void sendSimpleMail(String sendTo, String title, String content) throws UnsupportedEncodingException;

    /**
     * 发送带附件的简单邮件
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     */
    void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments);

    /**
     * 发送模板邮件
     * @param sendTo
     * @param titel
     * @param content
     * @param attachments
     */
    void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments);
}
