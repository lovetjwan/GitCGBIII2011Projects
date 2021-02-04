package com.cy.java.api.reflect;

import java.lang.annotation.*;

/**
 * 自定义注解
 * @Retention
 * @Target 注解用于描述这个注解可以描述哪些成员(类,属性,方法)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component{//@interface 描述的类型为注解类型(本质上是一个特殊的class)

}

@Component
class ClassA{}

public class AnnotationTests {
    public static void main(String[] args) throws Exception {
        //检测ClassA 上是否有@Component注解描述
        //1.获取类的字节码对象
        Class<?> cls = Class.forName("com.cy.java.api.reflect.ClassA");
        //2.判定对象上是否有@Component注解
        boolean flag = cls.isAnnotationPresent(Component.class);
        System.out.println(flag);//true
        if(flag){
            Object ob1 = cls.newInstance();//反射构建类的实例

        }
        //Annotation类型是所有注解的父类类型
        Annotation atn = cls.getAnnotation(Component.class);
        System.out.println(atn);
    }
}
