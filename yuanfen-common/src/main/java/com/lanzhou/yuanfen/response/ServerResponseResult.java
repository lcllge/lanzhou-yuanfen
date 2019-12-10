package com.lanzhou.yuanfen.response;

import java.io.Serializable;

/**
 * @version V1.0.0
 * @ClassName: {@link ServerResponseResult}
 * @Description: 后台返回统一DTO类
 * @author: 兰州
 * @date: 2019/7/16 9:22
 * @Copyright: @2019 All rights reserved.
 */
public class ServerResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -4539213916903487309L;

    private Integer code;

    private String message;

    private T data;

    public ServerResponseResult(Integer code) {
        this.code = code;
    }

    public ServerResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServerResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * success response
     *
     * @return
     */
    public static ServerResponseResult success() {
        return new ServerResponseResult(ServerResponseEnum.SUCCESS.getCode());
    }

    public static ServerResponseResult success(String message) {
        return new ServerResponseResult(ServerResponseEnum.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponseResult success(T data) {
        return new ServerResponseResult(ServerResponseEnum.SUCCESS.getCode(), data);
    }

    /**
     * fail response
     *
     * @return
     */
    public static ServerResponseResult fail() {
        return new ServerResponseResult(ServerResponseEnum.FAIL.getCode());
    }

    public static ServerResponseResult fail(String message) {
        return new ServerResponseResult(ServerResponseEnum.FAIL.getCode(), message);
    }

    public static <T> ServerResponseResult fail(T data) {
        return new ServerResponseResult(ServerResponseEnum.FAIL.getCode(), data);
    }

    /**
     * notfound response
     *
     * @return
     */
    public static ServerResponseResult notFound() {
        return new ServerResponseResult(ServerResponseEnum.NOTFOUND.getCode());
    }

    public static ServerResponseResult notFound(String message) {
        return new ServerResponseResult(ServerResponseEnum.NOTFOUND.getCode(), message);
    }

    public static <T> ServerResponseResult notFound(T data) {
        return new ServerResponseResult(ServerResponseEnum.NOTFOUND.getCode(), data);
    }

    /**
     * error response
     *
     * @return
     */
    public static ServerResponseResult error() {
        return new ServerResponseResult(ServerResponseEnum.ERROR.getCode());
    }

    public static ServerResponseResult error(String message) {
        return new ServerResponseResult(ServerResponseEnum.ERROR.getCode(), message);
    }

    public static <T> ServerResponseResult error(T data) {
        return new ServerResponseResult(ServerResponseEnum.ERROR.getCode(), data);
    }

    /**
     * unauthorized response
     *
     * @return
     */
    public static ServerResponseResult unauthorized() {
        return new ServerResponseResult(ServerResponseEnum.UNAUTHORIZED.getCode());
    }

    public static ServerResponseResult unauthorized(String message) {
        return new ServerResponseResult(ServerResponseEnum.UNAUTHORIZED.getCode(), message);
    }

    enum ServerResponseEnum {
        /**
         * 状态枚举
         */
        SUCCESS(200, "成功"),
        FAIL(201, "失败"),
        NOTFOUND(404, "未找到"),
        ERROR(500, "系统错误"),
        UNAUTHORIZED(403, "未授权");

        private int code;
        private String message;

        ServerResponseEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
