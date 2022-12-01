package com.zyz.example.constants;

public enum StateCode {

    OK(0, "请求成功"),

    SERVER_ERROR(10001, "网络异常"),

    NO_AUTH(10002, "没有权限"),

    /**
     * 参数不合法
     */
    ILLEGAL_PARAM(20001, "参数不合法"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND(50001, "用户不存在"),

    /**
     * 用户不存在
     */
    USER_FROZEN(50002, "用户不存在"),

    /**
     * 无token
     */
    LOGIN_NOT_TOKEN(50003, "无token，请重新登录"),

    /**
     * 参数有误
     */
    LOGIN_PARAM_ERROR(50004, "参数有误"),

    /**
     * 无效token
     */
    TOKEN_INVALID(50004, "无效token")



    ;


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StateCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
