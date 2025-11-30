package com.hmdp.config;

import com.hmdp.utils.RefreshTokenInterceptor;
import com.hmdp.utils.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
//告诉拦截器应该在里写网址使用，哪些不使用
public class MVCconfig implements WebMvcConfigurer {
    //获取spring中redis链接，并在下免费的方法中传入
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry
            /*此为拦截器注册器*/) {
        registry.addInterceptor(new loginInterceptor()).excludePathPatterns(
                "/user/code","/user/login",
                "blog/hot",
                "/shop/**","/shop-type/**",
                "/voucher/**",
                "/upload/**"
        ).order(1);
        //全部拦截的，先执行
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**").order(0);



    }
}
