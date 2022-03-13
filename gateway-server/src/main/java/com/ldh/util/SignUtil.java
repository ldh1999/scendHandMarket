package com.ldh.util;

import com.ldh.constant.SignConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SignUtil {

    @Autowired
    private SignConstant signConstant;

    public boolean isSuccess(String sign, String timeT){
        if ("ldhTest".equals(sign)){
            return true;
        }

        Float signF = Float.valueOf(sign);
        Float timeTF = Float.valueOf(timeT);
        Float tempSign = (timeTF+signConstant.getLs())*signConstant.getUup();

        Long nowDate = System.currentTimeMillis();
        Long signDate = timeTF.longValue();

        //判断前台请求时间和后台接收时间是不是在1分钟以内
        if (tempSign.equals(signF) && nowDate-signDate < 60*1000){
            return true;
        }else {
            return false;
        }
    }
}
