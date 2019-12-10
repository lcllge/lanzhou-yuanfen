package com.lanzhou.yuanfen.security.handler;

import com.alibaba.fastjson.JSON;
import com.lanzhou.yuanfen.response.ServerResponseResult;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Administrator
 */
@Log
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(ServerResponseResult.success()));
    }

}
