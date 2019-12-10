package com.lanzhou.yuanfen.security.filter;

import com.lanzhou.yuanfen.security.token.EmailAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AbstractAuthenticationProcessingFilter这个过滤器在前面一节介绍过，
 * 是UsernamePasswordAuthenticationFilter的父类，我们的IpAuthenticationProcessingFilter也继承了它
 * 构造器中传入了/emailVerify作为IP登录的端点
 * attemptAuthentication()方法中加载请求的IP地址，之后交给内部的AuthenticationManager去认证
 *
 * @author Administrator
 */
public class EmailAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final String DEFAULT_EMAIL_NAME = "email";
    private final String DEFAULT_EMAIL_CODE = "eCode";

    /**
     * 使用/emailVerify该端点进行ip认证
     */
    public EmailAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/emailVerify"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 获取host信息
        String email = request.getParameter(DEFAULT_EMAIL_NAME);
        String eCode = request.getParameter(DEFAULT_EMAIL_CODE);
        //交给内部的AuthenticationManager去认证，实现解耦
        return getAuthenticationManager().authenticate(new EmailAuthenticationToken(email, eCode));
    }


}
