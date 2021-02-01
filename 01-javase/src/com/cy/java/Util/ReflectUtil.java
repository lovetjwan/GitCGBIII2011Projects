package com.cy.java.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtil {

    /**
     * 基于传入的类型构建对象实例
     */
    public static Object newInstance(String pkgClass,
                                     Object[]args,
                                     Class<?>... parameterTypes) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cls=Class.forName(pkgClass);
        Constructor c=cls.getDeclaredConstructor(parameterTypes);
        c.setAccessible(true);
        return c.newInstance(args);
    }
}
