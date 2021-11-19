package com.ldh.listenerModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Slf4j
@Accessors(chain = true)
public class UpLoadImageListenerDTO implements Serializable {
    /** 文件组 */
    private String imgGroup;
    /** 文件名 */
    private String imgName;
    /** 对象id */
    private String objectId;

    private String fileJson;
}
