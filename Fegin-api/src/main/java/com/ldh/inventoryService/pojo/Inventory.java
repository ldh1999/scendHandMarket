package com.ldh.inventoryService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
public class Inventory implements Serializable {
    /** 商品id*/
    private String id;
    /** 商家id */
    private String merchantId;
    /** 商品名称 */
    private String inventoryName;
    /** 商品信息 */
    private String inventoryInformation;
    /** 商品单价 */
    private Float inventoryPrice;


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
