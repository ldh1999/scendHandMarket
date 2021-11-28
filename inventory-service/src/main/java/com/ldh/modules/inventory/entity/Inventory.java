package com.ldh.modules.inventory.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
public class Inventory implements Serializable {

    public Inventory() {
    }

    /** 深拷贝 */
    public Inventory(Inventory inventory) {
        this.id = inventory.id;
        this.merchantId = inventory.merchantId;
        this.inventoryName = inventory.inventoryName;
        this.inventoryInformation = inventory.inventoryInformation;
        this.inventoryPrice = inventory.inventoryPrice;
        this.sts = inventory.sts;
        this.remark = inventory.remark;
        this.createBy = inventory.createBy;
        this.createTime = inventory.createTime;
        this.updateBy = inventory.updateBy;
        this.updateTime = inventory.updateTime;
    }

    /** 商品id*/
    @TableId(type = IdType.UUID)
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



