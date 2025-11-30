package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {
    public  static  final long BEGIN_TIME=1640995200L;
    private StringRedisTemplate stringRedisTemplate;
    private  static final int COUNT_BITS=32;


    public RedisIdWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public  long nextId(String keyPrefix){
        //生成时间戳
        LocalDateTime now=LocalDateTime.now();
        long nowsecong=now.toEpochSecond(ZoneOffset.UTC);//现在的时间戳
        long timetamp=nowsecong-BEGIN_TIME;//相差的时间戳，为了不被找出规律
        //生成序列号
        String date=now.format(DateTimeFormatter.ofPattern("yyyy:MMM;dd"));
      long count=stringRedisTemplate.opsForValue().increment("icr:"+keyPrefix+":"+date);
        //拼接并返回
        return timetamp<<COUNT_BITS|count;
    }

}
