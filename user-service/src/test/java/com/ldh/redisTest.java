package com.ldh;

import com.ldh.api.GD.client.LocationClient;
import com.ldh.api.GD.model.LocationResponse;
import com.ldh.inventoryService.client.InventoryClient;
import key.GaoDe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class redisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private LocationClient locationClient;

    @Test
    public void redisTeest(){
       //redisTemplate.opsForValue().set("key","value",20, TimeUnit.SECONDS);
        //System.out.println(redisTemplate.opsForValue().get("test"));
    }

    @Test
    public void feginTest(){
        locationClient.getNameByLocation(GaoDe.key, "117.184609,39.117364",null);
    }
}
