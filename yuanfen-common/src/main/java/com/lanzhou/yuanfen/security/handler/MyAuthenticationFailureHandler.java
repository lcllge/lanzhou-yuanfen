package com.lanzhou.yuanfen.security.handler;

import com.alibaba.fastjson.JSON;
import com.lanzhou.yuanfen.response.ServerResponseResult;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Administrator
 */
@Log
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");
        // response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        ServerResponseResult result;
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            result = ServerResponseResult.fail("用户名或密码输入错误，登录失败!");
        } else {
            result = ServerResponseResult.fail("登录失败, 请稍后重试 !");
        }
        response.getWriter().write(JSON.toJSONString(result));
    }

}
