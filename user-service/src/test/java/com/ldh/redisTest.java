package com.ldh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class redisTest {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTeest(){

        redisTemplate.opsForValue().set("key","value",20, TimeUnit.SECONDS);
        //System.out.println(redisTemplate.opsForValue().get("test"));
    }
}
