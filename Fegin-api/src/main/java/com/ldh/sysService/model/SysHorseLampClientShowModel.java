package com.ldh.sysService.model;

import com.ldh.sysService.pojo.SysHorseLamp;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Slf4j
public class SysHorseLampClientShowModel extends SysHorseLamp implements Serializable {
    /** 图片路径 */
    private String imagePath;

}
