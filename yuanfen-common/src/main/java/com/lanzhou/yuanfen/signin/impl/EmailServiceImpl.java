package com.lanzhou.yuanfen.signin.impl;

import com.lanzhou.yuanfen.signin.EmailService;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Administrator
 */
@Service
public class EmailServiceImpl implements EmailService {


    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private MailProperties mailProperties;

    @Override
    public void sendSimpleMail(String subject, String text, String targetEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setTo(targetEmail);

        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
}
