package com.cy;

import com.cy.pj.sys.service.realm.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching //启动springboot内置缓存
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }

    /**
     * 配置Realm对象（org.apache.shiro.realm.Realm）
     * @Bean 注解描述方法时，表示方法返回值交给spring管理
     */
    @Bean
    public Realm realm(){
        return new ShiroRealm();
    }

    /**定义过滤规则（Shiro框架中提供了很多过滤器-Filter，它会对i请求中的信息进行
     * 过滤，假如这些请求需要认证才可访问，则需要先登录认证），例如在一些订票系统中
     * 查询票信息不需要登录，但是在访问订单信息或进行订单创建时则需要登录才能进行访问
     * 这就是规则*/
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        //过滤链对象的定义（这个过滤链中包含了很多内置过滤器）
        DefaultShiroFilterChainDefinition chainDefinition =
                new DefaultShiroFilterChainDefinition();
        //指定过滤链中的过滤规则，例如：
        //配置/menu/*开头的资源，可以匿名访问（不用登录就可以访问）,其中anon为shiro框架指定的匿名过滤器
//        chainDefinition.addPathDefinition("/menu/**","anon");
        //配置以/**开头的资源必须都要经过认证，其中authc为shiro框架指定的认证过滤器
        chainDefinition.addPathDefinition("/**","authc");
        return chainDefinition;
    }
}
