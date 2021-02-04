package com.cy.java.api.reflect;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class PackageTests {
    public static void main(String[] args) {
        //获取类的字节码对象
        Class<?> c = PackageTests.class;
        //获取类所在的包对象
        Package aPackage = c.getPackage();
        //获取具体的包名
        String name = aPackage.getName();
        System.out.println(name);
        //将包结构转换为目录结构
        String dirName = name.replace(".","/");
        System.out.println(dirName);//com/cy/java/api/reflect
        //通过类加载器
        URL url = ClassLoader.getSystemClassLoader().getResource(dirName);
        System.out.println(url.getPath());
        //将类路径的类创建实例存放到内存
//        URLClassLoader.getSystemResource(url.getPath());
//        new FileInputStream(new File(url.getPath()));
        //获取路径对应的文件File对象
        File file = new File(url.getPath());
        //获取目录下的文件名
        String[] files = file.list();
        Arrays.toString(files);
    }
}
