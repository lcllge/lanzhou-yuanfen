package com.lanzhou.yuanfen.security.handler;

import com.alibaba.fastjson.JSON;
import com.lanzhou.yuanfen.response.ServerResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LanZhou
 */
@Configuration
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        ServerResponseResult unauthorized = ServerResponseResult.unauthorized("权限不足，请联系管理员!");
        out.write(JSON.toJSONString(unauthorized));
        out.flush();
        out.close();
    }
}
