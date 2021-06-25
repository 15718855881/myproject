package com.chinaoly.city.common;

/**
 * @author zhangyahui
 * @date 2021/6/24
 */
public enum DemoErrors implements ServiceErrors {
    /**
     * 错误码
     */
    SYSTEM_ERROR(1000, "系统错误"),
    PARAM_ERROR(1001, "用户名，密码不能为空"),
    ;
    private Integer code;

    private String message;

    DemoErrors(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
