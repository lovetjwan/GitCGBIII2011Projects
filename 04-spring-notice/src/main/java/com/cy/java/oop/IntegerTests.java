package com.cy.java.oop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class IntegerTests {

    public static void main(String[] args) throws Exception {
        Integer n1 = 100;//Integer.valueOf(100)
        Integer n2 = 100;
        Integer n3 = 200;
        Integer n4 = 200;
        System.out.println(n1==n2);
        System.out.println(n3==n4);
//        Class<?> cls = Class.forName("java.lang.Integer$IntegerCache");
//        System.out.println(cls);
//        Constructor<?> con = cls.getDeclaredConstructor(Integer.class);
//        con.setAccessible(true);
//        Object obj = con.newInstance(n1);
//        Field field = cls.getDeclaredField("cache");
//        field.setAccessible(true);
//        System.out.println(field.get(obj));
    }
}
