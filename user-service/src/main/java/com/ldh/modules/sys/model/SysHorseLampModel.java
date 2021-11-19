package com.ldh.modules.sys.model;

import com.ldh.modules.sys.entity.SysHorseLamp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class SysHorseLampModel extends SysHorseLamp implements Serializable{

    /** 类型名称 */
    private String horseTypeName;

    /** 对象名 */
    private String ObjectName;

    /** 状态名称 */
    private String stsName;

    /** 图片路径 */
    private String imagePath;
}
