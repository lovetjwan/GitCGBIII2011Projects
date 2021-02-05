package com.cy.pj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，希望通过此注解对一些业务方法做标记(对方法进行描述)
 * 在面向切面编程中将这些做好标记的方法做为我们的切入点方法.
 */
@Retention(RetentionPolicy.RUNTIME) //运行时有效
@Target(ElementType.METHOD) //此注解仅限于方法
public @interface RequiredLog {
    String operation();
}
