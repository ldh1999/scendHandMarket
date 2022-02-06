package com.ldh.modules.shop.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 购物偏好
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */
@Data
@TableName("shop_preferences_type")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="shop_preferences_type对象", description="购物偏好")
public class ShopPreferencesType implements Serializable{
    
	/**偏好id*/
    @ApiModelProperty(value = "偏好id")
	@TableId(type = IdType.UUID)
	private String shopPreferencesTypeId;
	/**偏好类型*/
    @ApiModelProperty(value = "偏好类型")
	private String inventoryTypeId;
	/**用户id*/
    @ApiModelProperty(value = "用户id")
	private String userId;
	/**偏好值*/
    @ApiModelProperty(value = "偏好值")
	private Float preferencesValue;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
	/**remark*/
    @ApiModelProperty(value = "remark")
	private String remark;
}
