package com.ldh.inventoryService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Merchant implements Serializable {
    /** 店铺id */

    private String merchantId;
    /** 用户id */
    private String authorityId;
    /** 店铺名 */
    private String merchantName;
    /** 备案电话 */
    private String recordPhone;
    /** 备案身份证 */
    private String recordIdentityCode;
    /** 备案真实姓名 */
    private String recordRealName;
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
