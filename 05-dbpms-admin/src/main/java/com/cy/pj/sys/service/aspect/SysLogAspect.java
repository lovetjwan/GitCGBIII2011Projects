package com.cy.pj.sys.service.aspect;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.sys.pojo.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Spring框架中由@Aspect注解描述的类型为切面类型，此切面类型中要有
 * 1、切入点的定义（用于约束在哪些方法执行时，进行功能拓展）
 * 2、通知方法的定义（这样的方法中会封装要执行的扩展业务逻辑，例如日志记录）
 */
@Order(2)//数字越小优先级越高
@Slf4j
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
        System.out.println("SysLogAspect.@Around.Before");
        long t1 = System.currentTimeMillis();
        log.info("before {}",t1);
        try {
            Object result = joinPoint.proceed();//调用切入点方法
//        System.out.println("after:"+System.currentTimeMillis());
            long t2 = System.currentTimeMillis();
            log.info("after {}",t2);
            //记录详细的正常行为日志
            doLogInfo(joinPoint,t2-t1,null);
            return result; //此返回值会交给代理对象，代理对象传给调用方法
        } catch (Throwable e){
            e.printStackTrace();
            long t3 = System.currentTimeMillis();
            log.error("exception {}",System.currentTimeMillis());
            //记录详细的异常行为日志
            doLogInfo(joinPoint,t3-t1,e);
            throw e;
        }
    }

    //记录详细的用户操作日志
    private void doLogInfo(ProceedingJoinPoint joinPoint, long time, Throwable e) throws Throwable {
        //1、获取日志
        //1.1 获取登录用户名（还没做登录时，先给假数据）
        String username = "tony";
        //1.2 获取ip（不会获取ip地址时先给假数据）
        String ip = "202.106.0.29";
        //1.3 获取操作切入点方法上RequiredLog注解中operation属性值
        //1.3.1 获取目标对象对应的字节码对象
        Class<?> targetCls = joinPoint.getTarget().getClass();
        //1.3.2 获取目标方法对象
        //1.3.2.1 获取方法签名
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        //1.3.2.2 通过字节码对象以及方法信息获取目标方法对象
//        Method targetMethod = ms.getMethod();
        Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        //1.3.3 获取方法上的requiredLog注解
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        //1.3.4 获取注解中operation属性的值
        String operation = requiredLog.operation();
        //1.4 获取目标方法信息（切入点方法的信息（方法所在的类+方法名））
        String targetClsMethod = targetCls.getName() + "." + targetMethod.getName();
        //1.5 获取方法参数
        //1.5.1 获取实际参数值
        Object[] args = joinPoint.getArgs();
        //1.5.2 将参数值转换为json格式字符串(一种常用的数据格式)
        String jsonParamStr = new ObjectMapper().writeValueAsString(args);
        //2、封装日志
        SysLog userLog = new SysLog();
        userLog.setUsername(username);
        userLog.setIp(ip);
        userLog.setOperation(operation);//获取切入点方法上RequiredLog注解中operation属性值
        userLog.setMethod(targetClsMethod);//获取切入点方法的信息(方法所在的类+方法名)
        userLog.setParams(jsonParamStr);//执行方法时调用的参数
        if (e!=null){
            userLog.setStatus(0);//操作状态
            userLog.setError(e.getMessage());//错误信息
        }
        userLog.setTime(time);//执行时长
        userLog.setCreatedTime(new Date());//创建日志时间
        //3、记录日志
        log.info("user.oper {}",new ObjectMapper().writeValueAsString(userLog));
        sysLogService.saveLog(userLog);
//        new Thread(() -> sysLogService.saveLog(userLog)).start();
        //并发比较小时，可以自己new线程执行写日志这个耗时操作
//        new Thread(){
//            @Override
//            public void run() {
//                sysLogService.saveLog(userLog);
//            }
//        }.start();
        //假如并发比较大,则需要使用线程池
    }

    @Autowired
    private SysLogService sysLogService;


}
