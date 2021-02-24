package com.cy;

import com.cy.pj.sys.service.realm.ShiroRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching //启动springboot工程中的内置缓存
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
    /**配置Realm对象(org.apache.shiro.realm.Realm)
     * @Bean注解描述方法时，表示方法的返回值要交给spring管理。
     */
    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    /**定义过滤规则(Shiro框架中提供了很多过滤器-Filter,它会对请求中的信息进行
     * 过滤，假如这些请求需要认证才可访问，则需要先登录认证)，例如在一些订票系统
     * 中，查询票信息不需要登陆，但是访问订单信息或进行订单创建时则需要登录，这些
     * 都称之为规则
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        //过滤链对象的定义(这个过滤链中包含了很多内置过滤器)
        DefaultShiroFilterChainDefinition chainDefinition =
                new DefaultShiroFilterChainDefinition();
        //指定过滤链中的过滤规则,例如：
        //配置/user/login/**开头的资源，可以匿名访问(不用登录就可以访问),其中anon为shiro框架指定的匿名过滤器
        chainDefinition.addPathDefinition("/user/login/**","anon");
        //配置登出操作，logout为shiro提供的一个默认登出过滤器
//        chainDefinition.addPathDefinition("/user/logout","logout");
        //配置以/**开头的资源必须都要经过认证，其中authc为shiro框架指定的认证过滤器
        //chainDefinition.addPathDefinition("/**", "authc");
        //假如配置了记住我功能,则需将过滤器authc替换为user
//        chainDefinition.addPathDefinition("/**", "user");
        return chainDefinition;
    }

    /**配置session管理器对象*/
    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        //session的超时时间
        sessionManager.setGlobalSessionTimeout(1000*60*60);//1个小时
        //sessionManager.setGlobalSessionTimeout(2*60*1000);//2分钟
        //删除无效session
        sessionManager.setDeleteInvalidSessions(true);
        //当客户端cookie被禁用是否要设置url重写
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**65
     * 记住我
     * @return
     */
    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager cManager=new CookieRememberMeManager();
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        cookie.setMaxAge(7*24*60*60);//7天
        cManager.setCookie(cookie);
        //设置加密解密密钥，用于服务器重启后是否更改密钥，或固定密钥
        //当设置密钥时，服务器重启后客户端不会强制下线，不需要重新登录
        //当把密钥交给系统去管理，则服务器重启后客户端是需要重新登录的
        cManager.setCipherKey(Base64.decode("zSyK5Kp6PZAAjlT+eeNMlg=="));
        return cManager;
    }

    /**
     * 配置Shiro中自带的CacheManager对象,此对象可以通过
     * Cache缓存授权时获取到权限信息,下次再访问授权方法
     * 时不需要再查用户权限.
     * @return
     */
    @Bean
    protected CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

}
