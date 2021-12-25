package com.ldh.modules.merchant.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Merchant implements Serializable {
    /** 店铺id */
    @TableId(type = IdType.UUID)
    private String merchantId;
    /** 店铺编号 */
    private String merchantCode;
    /** 用户id */
    private String authorityId;
    /** 店铺名 */
    private String merchantName;
    /** 店铺介绍 */
    private String merchantInformation;
    /** 备案电话 */
    private String recordPhone;
    /** 备案身份证 */
    private String recordIdentityCode;
    /** 备案真实姓名 */
    private String recordRealName;
    private String sts;


    private String remark;

    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
