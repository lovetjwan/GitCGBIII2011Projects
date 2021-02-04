package com.cy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类在运行时的作用：
 * 1、通过ClassLoader(类加载器 --负责将磁盘中的类读到内存中)将类加载到内存
 * 2、通过线程(thread)调用io(InputStream)从磁盘(Disk)读取文件(File)信息
 * 3、读取类上的描述(@Component,@Service,@Controller,.....),并基于描述构建
 * 配置对象(BeanDefinition)，存储类的配置信息(类全名,作用域,....).
 * 4、基于类的配置信息通过Bean工厂构建类的实例(对象),并进行存储(对象池 -- 用时从池中取)
 * 5、当我们需要一个类的实例时可以从对象池(Bean池)获取即可.
 * JVM 参数分析
 * 1、检测类的加载：-XX:+TraceClassLoading
 */
//@MapperScan("com.cy.pj.sys.dao")
@EnableAsync //此注解用于告诉springboot。启动时初始化一个线程池(ThreadPoolExecutor)
@SpringBootApplication
public class NoticeApplication {//Object.class

    @Autowired
    private BeanFactory beanFactory;

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

}
/**
 * 记住：我们要交给spring容器管理的对象，一定要放在启动类所在包或子包中
 * 然后使用特性注解进行描述(@Component,@Service,...)
 *
 * FAQ？
 * Spring是一个资源管理框架，请问资源是谁？对象
 */
