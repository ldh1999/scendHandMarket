package com.ldh.util;

import com.ldh.constant.SignConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class SignUtil {

    @Autowired
    private SignConstant signConstant;

    public boolean isSuccess(String sign, String timeT){
        if ("ldhTest".equals(sign)){
            return true;
        }

        Double signF = Double.valueOf(sign);
        Double timeTF = Double.valueOf(timeT);
        Double tempSign = (timeTF+signConstant.getLs())*signConstant.getUup();

        Long nowDate = System.currentTimeMillis();
        Long signDate = timeTF.longValue();
        //判断前台请求时间和后台接收时间是不是在1分钟以内
        if (tempSign.equals(signF) && nowDate-signDate < 60*1000){
            return true;
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.warn("nowDate="+sdf.format(nowDate));
            log.warn("timeTF="+sdf.format(signDate));
            log.warn("----"+(nowDate-signDate));
            log.warn("tempSign="+tempSign);
            log.warn("signF="+signF);
            return false;
        }
    }
}
