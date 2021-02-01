package com.cy.java.oop;

import com.cy.java.Util.ReflectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Point{
    private int x;
    private int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class ReflectTests {

    public static void main(String[] args) throws Exception {
        //作业:
        //1.通过反射构建Point类型的实例对象
        //1.1获取反射应用的起点对象(类的字节码对象)
        Class clas = Point.class;
        //1.2基于字节码对象获取类的构造方法对象
//        Constructor con = clas.getConstructor(int.class,int.class);
//        Point obj = (Point) con.newInstance(1,2);
//        System.out.println(obj);
        Point obj= (Point) ReflectUtil.newInstance("com.cy.java.oop.Point",
                new Object[]{10,20},
                int.class,int.class);
        obj.setX(100);//自己手动赋值
        obj.setY(200);
        //2.通过反射为point类实例的属性直接赋值
        Field f1 = clas.getDeclaredField("x");
        f1.setAccessible(true);//设置可访问
        f1.set(obj,1000);//为point对象的f1属性赋值为1000
        System.out.println(obj);
        //3.通过反射调用point类的实例方法为属性赋值
        Method m1 = clas.getMethod("setX",int.class);
        Method m2 = clas.getMethod("setY",int.class);
        m1.invoke(obj,3);
        m2.invoke(obj,4);
        //4.通过反射调用point类的get方法获取属性值.
        Method m3 = obj.getClass().getMethod("getX");
        Method m4 = obj.getClass().getMethod("getY");
        System.out.println(m3.invoke(obj));
        System.out.println(m4.invoke(obj));
    }
}
