package com.ldh.modules.upload.model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.lang.reflect.Field;

@Slf4j
@Data
@Accessors(chain = true)

public class ImageGetVO implements Serializable {
    private String objectId;
    private String imgGroup;
    private Integer sort;

    public boolean isNotNull(){
        boolean flag = true;
        if (!StringUtils.isNotEmpty(this.objectId)){
            flag = false;
        }
        if (!StringUtils.isNotEmpty(this.imgGroup)){
            flag = false;
        }
        return flag;
    }
     /*Class clazz = ImageGetVO.class;
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if (field.get())
        }*/

}
