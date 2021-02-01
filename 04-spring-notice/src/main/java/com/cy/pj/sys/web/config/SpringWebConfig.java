package com.cy.pj.sys.web.config;

import com.cy.pj.sys.web.interceptor.TimeAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //此注解为spring中的一个配置类bean对象
public class SpringWebConfig implements WebMvcConfigurer {

    //注册拦截器,并且设置要拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
                //注册拦截器（将拦截器添加到spring容器）
        registry.addInterceptor(new TimeAccessInterceptor())
                //设置要拦截的url
//                .addPathPatterns("/notice/**"); //*表示通配符，**表示多层url
                .addPathPatterns("/notice/doFindNotices","/notice/doDeleteById/*");
    }
}
