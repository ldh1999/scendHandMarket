package com.ldh.orderService.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 快递员
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="courier对象", description="快递员")
public class Courier implements Serializable{

	/**id*/
	@ApiModelProperty(value = "id")
	private String courierId;
	/**物流公司id*/
	@ApiModelProperty(value = "物流公司id")
	private String courierServicesCompanyId;
	/**快递员编号*/
	@ApiModelProperty(value = "快递员编号")
	private String courierCode;
	/**快递员外部标识号*/
	@ApiModelProperty(value = "快递员外部标识号")
	private String courierExternalIdentifierCode;
	/**快递员姓名*/
	@ApiModelProperty(value = "快递员姓名")
	private String courierName;
	/**快递员手机号*/
	@ApiModelProperty(value = "快递员手机号")
	private String courierPhone;
	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
	/**sts*/
	@ApiModelProperty(value = "sts")
	private String sts;
	/**createTime*/
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
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
