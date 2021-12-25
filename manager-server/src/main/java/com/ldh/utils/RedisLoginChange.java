package com.ldh.utils;

import com.ldh.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

public class RedisLoginChange {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getNowUser(String stuId){
        if (redisTemplate.opsForHash().hasKey(RedisConstant.USER_LOGIN_ALL,stuId)){
            return redisTemplate.opsForHash().get(RedisConstant.USER_LOGIN_ALL,stuId);
        }
        return null;
    }

}
