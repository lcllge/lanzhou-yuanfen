package com.lanzhou.yuanfen.signin;


/**
 * @author Administrator
 */
public interface EmailService {

    /**
     * 发送简单的文字
     *
     * @param subject     标题
     * @param text        内容
     * @param targetEmail 目标地址
     */
    void sendSimpleMail(String subject, String text, String targetEmail);

}
