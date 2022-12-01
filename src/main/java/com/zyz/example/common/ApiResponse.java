package com.zyz.example.common;

import com.zyz.example.constants.StateCode;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    private static final int SUCCESS_CODE = 0;
    private static final int FAIL_CODE = 500;

    private T data;
    private Integer code;
    private String msg;
    private Long currentTime;

    public static<T> ApiResponse<T> ok(){
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setCode(SUCCESS_CODE);
        apiResponse.setMsg("success");
        apiResponse.setCurrentTime(System.currentTimeMillis());
        return apiResponse;
    }

    public static<T> ApiResponse<T> ok(T data){
        ApiResponse<T> apiResponse = ok();
        apiResponse.setData(data);
        return apiResponse;
    }

    public static<T> ApiResponse<T> error(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setCode(FAIL_CODE);
        apiResponse.setMsg(message);
        apiResponse.setCurrentTime(System.currentTimeMillis());
        return apiResponse;
    }

    public static<T> ApiResponse<T> error(Integer code, String message) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setCode(code);
        apiResponse.setMsg(message);
        apiResponse.setCurrentTime(System.currentTimeMillis());
        return apiResponse;
    }

    public static<T> ApiResponse<T> error(StateCode stateCode) {
        return error(stateCode.getCode(), stateCode.getMsg());
    }
}
