package com.ldh.modules.informationMaintenance.entity;

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
 * @Description: 快递员
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@TableName("courier")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="courier对象", description="快递员")
public class Courier implements Serializable{
    
	/**id*/
	@TableId(type = IdType.UUID)
    @ApiModelProperty(value = "id")
	private Integer courierId;
	/**物流公司id*/
    @ApiModelProperty(value = "物流公司id")
	private Integer courierServicesCompanyId;
	/**快递员编号*/
    @ApiModelProperty(value = "快递员编号")
	private String courierCode;
	/**快递员姓名*/
    @ApiModelProperty(value = "快递员姓名")
	private String courierName;
	/**快递员手机号*/
    @ApiModelProperty(value = "快递员手机号")
	private String courierPhone;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
	/**createTime*/
	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@TableField(fill = FieldFill.UPDATE)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
