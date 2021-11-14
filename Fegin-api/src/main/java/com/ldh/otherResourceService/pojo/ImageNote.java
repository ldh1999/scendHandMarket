package com.ldh.otherResourceService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
@Accessors(chain = true)
public class ImageNote implements Serializable {

    private String id;
    /** 文件路径 */
    private String imgPath;
    /** 文件组 */
    private String imgGroup;
    /** 文件名 */
    private String imgName;
    /** 对象id */
    private String objectId;
    /** 排序 */
    private Integer sort;
    private String sts;


    private String remark;

    private String createBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String updateBy;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
