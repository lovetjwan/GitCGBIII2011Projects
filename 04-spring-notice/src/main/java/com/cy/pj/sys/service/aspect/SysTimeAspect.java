package com.cy.pj.sys.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通过此切面演示各种通知(Before,After,Aroud,AfterReturning,AfterThrowing)
 * 的执行时间点
 */
@Order(1)//数字越小优先级越高
@Aspect
@Component
public class SysTimeAspect {

    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredTime)")
    public void doTime(){}

    //在目标方法前执行
    @Before("doTime()")
    public void doBefore(){
        System.out.println("@Before");
    }

    //在目标方法之后执行(不管成功与否)
    @After("doTime()")
    public void doAfter(){
        System.out.println("@After");
    }

    //在目标方法执行成功后执行
    @AfterReturning("doTime()")
    public void doAfterReturning(){
        System.out.println("@AfterReturning");
    }

    //在目标方法执行失败后执行
    @AfterThrowing("doTime()")
    public void doAfterThrowing(){
        System.out.println("@doAfterThrowing");
    }

    //环绕方法
    @Around("doTime()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        //这个会被先执行是因为这时候还没有调用目标方法
        System.out.println("@Around.before");
        System.out.println("SysTimeAspect.@Around.Before");
        try {
            //注意：在没有调用这个方法前，其他通知方法并不会执行
            Object result = joinPoint.proceed();//调用本类其它通知方法,后续其它切面方法
            System.out.println("@Around.AfterReturning");
            return result;
        }catch (Throwable e){
            System.out.println("@Around.@AfterThrowing");
            throw e;
        }finally {
            System.out.println("@Around.@After");
        }
    }
}
