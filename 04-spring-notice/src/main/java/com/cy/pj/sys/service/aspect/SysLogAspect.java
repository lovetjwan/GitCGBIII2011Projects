package com.cy.pj.sys.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Spring框架中由@Aspect注解描述的类型为切面类型，此切面类型中要有
 * 1、切入点的定义（用于约束在哪些方法执行时，进行功能拓展）
 * 2、通知方法的定义（这样的方法中会封装要执行的扩展业务逻辑，例如日志记录）
 */
@Aspect
@Component
public class SysLogAspect {

    /**
     * @Pointcut注解用于定义切入点
     * @annotation(注解类全名) 为定义切入点的一种表达式,由表达式中注解描述
     * 的方法为切入点方法，可以在目标业务执行时通过指定的通知方法进行功能增强
     */
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public void doLog(){ }//此方法只负责承载切入点，不需要写方法实现

    /**
     * @Around 注解描述的方法为一个用于封装拓展业务逻辑的方法，此方法中
     * 可以通过连接点对象调用目标方法
     * @param joinPoint 表示一个连接点对象，此对象封装了切入点方法信息(目标方法信息)
     *                  可以通过连接点对象调用切入点方法（目标方法）
     * @return 目标方法的返回结果。
     */
    @Around("doLog()")//@Around("@annotation(com.cy.pj.common.annotation.RequiredLog)")
    public Object doLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before:"+System.currentTimeMillis());
        Object result = joinPoint.proceed();//调用切入点方法
        System.out.println("after:"+System.currentTimeMillis());
        return result; //此返回值会交给代理对象，代理对象传给调用方法
    }
}
