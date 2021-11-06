package com.ldh.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class RedisSessionUtil {
    public static Object sessionAttributeToEntity(Object sessionAttribute, Class entityClass){
        Map map = (Map) sessionAttribute;
        if (map.containsKey("@type")){
            map.remove("@type");
        }
        Object entity = JSON.parseObject(JSON.toJSONString(map), entityClass);
        return entity;
    }
}
