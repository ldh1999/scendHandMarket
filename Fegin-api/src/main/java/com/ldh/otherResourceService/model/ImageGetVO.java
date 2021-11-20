package com.ldh.otherResourceService.model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Slf4j
@Data
@Accessors(chain = true)
public class ImageGetVO implements Serializable {
    private String objectId;
    private String imgGroup;
    private Integer sort;

     /*Class clazz = ImageGetVO.class;
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if (field.get())
        }*/

}
