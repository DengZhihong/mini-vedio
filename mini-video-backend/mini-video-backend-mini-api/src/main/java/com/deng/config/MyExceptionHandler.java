package com.deng.config;

import com.deng.utils.JSONResult;
import exception.MyInternalException;
import exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * 全局异常处理器,全部异常统统拦截到这里处理
 */
@RestControllerAdvice
public class MyExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常,全局异常处理器拦截后,会根据@ExceptionHandler分配到对应的处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyInternalException.class)
    public JSONResult handleMyInternalException(MyInternalException e) {

        logger.error("INTERNAL_EXCEPTION ---------> {}", e.getMessage());
        return JSONResult.errorMsg(e.getMessage());
    }
}