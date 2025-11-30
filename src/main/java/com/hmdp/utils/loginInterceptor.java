package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

public class loginInterceptor implements HandlerInterceptor {
//    //这个类是自己所写的类，不受spring管控，所以无自动注入与@resource，但是他的上级
//    // mvcconfig是受spring管
//    private StringRedisTemplate stringRedisTemplate;
////接收mvcconfig传入的redis连接
//    public loginInterceptor(StringRedisTemplate stringRedisTemplate) {
//        this.stringRedisTemplate = stringRedisTemplate;
//    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//分为两步：全部拦截，并获得+此部分拦截
//        //get 请求头中的token
//        String token = request.getHeader("authorization");
//        if (token == null) {
//            //返回状态码，拦截
//            response.setStatus(401);
//            return false;
//        }
//        //用token获取redis里的放入的user
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash()
//                .entries(LOGIN_USER_KEY + token);
//        if (userMap.isEmpty()) {
//            //返回状态码，拦截
//            response.setStatus(401);
//            return false;
//        }
//        //讲查到的redis中的hash对象转化为userDTO
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
//        //如果有，则经user信息放入Thread,这里将页面输入的user，copy成了UderDTO来防止数据泄露
//        UserHolder.saveUser(userDTO);
//        //刷新token有效期
//        stringRedisTemplate.expire(LOGIN_USER_KEY+token,LOGIN_USER_TTL, TimeUnit.MINUTES);
        if(UserHolder.getUser() == null) {
            response.setStatus(401);
            return false;
        }

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        UserHolder.removeUser();

    }
}
