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
 * @Description: 快递公司维护
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="courier_services_company对象", description="快递公司维护")
public class CourierServicesCompany implements Serializable {
    
	/**id*/
    @ApiModelProperty(value = "id")
	private String courierServicesCompanyId;
	/**快递公司编码*/
    @ApiModelProperty(value = "快递公司编码")
	private String courierServicesCompanyCode;
	/**快递公司名称*/
    @ApiModelProperty(value = "快递公司名称")
	private String courierServicesCompanyName;
	/**快递公司联系电话*/
    @ApiModelProperty(value = "快递公司联系电话")
	private String courierServicesCompanyPhone;
	/**remark*/
    @ApiModelProperty(value = "remark")
	private String remark;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
