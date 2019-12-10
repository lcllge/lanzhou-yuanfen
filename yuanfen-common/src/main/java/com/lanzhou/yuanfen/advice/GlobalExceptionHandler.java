package com.lanzhou.yuanfen.advice;

import com.lanzhou.yuanfen.response.ServerResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link RestController} 全局异常处理
 *
 * @author lcllge
 * @date 2019/3/8 15:31
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ServerResponseResult errorHandler(Exception ex) {
        log.error("请求出错 , 报错原因为: ", ex);
        return ServerResponseResult.error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ServerResponseResult unauthorizedExceptionHandler(HttpServletRequest request, Exception e) {
        return ServerResponseResult.unauthorized("暂无权限");
    }

}
