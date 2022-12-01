package com.zyz.example.aop;

import com.zyz.example.common.ApiResponse;
import com.zyz.example.constants.StateCode;
import com.zyz.example.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final  static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *自定义异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ApiResponse<Void> customException(BaseException e){
        log.error("code={},msg={}", e.getCode(), e.getMsg(), e);
        return ApiResponse.error(e.getCode(),e.getMessage());
    }

    /**
     * 默认异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object defaultException(Exception e) {
        String msg = e.getMessage();
        log.error(e.getMessage(), e);
        if (StringUtils.isEmpty(msg)) {
            msg = StateCode.SERVER_ERROR.getMsg();
        }
        return ApiResponse.error(StateCode.SERVER_ERROR.getCode(), msg);
    }

    /**
     * 请求类型不支持异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResponse<Void> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        log.error(e.getMessage(), e);
        return ApiResponse.error("不支持该请求类型");
    }
}
