package com.cy.pj.common.web;

import com.cy.pj.common.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 此注解描述的类为全局异常处理类
 */
@RestControllerAdvice//@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @ExceptionHandler(IllegalArgumentException.class)
//    public JsonResult doHandleRuntimeException(IllegalArgumentException e){
//        e.printStackTrace();
//        log.error("exception msg is {}",e.getMessage());
//        return new JsonResult(e);
//    }

    /**
     * @ExceptionHandler注解描述的方法为异常处理方法
     * 注解中定义的异常类型为方法可以处理的异常类型。
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandleRuntimeException(RuntimeException e){
        e.printStackTrace();
        log.error("exception msg is {}",e.getMessage());
        return new JsonResult(e);
    }
    //......
}
