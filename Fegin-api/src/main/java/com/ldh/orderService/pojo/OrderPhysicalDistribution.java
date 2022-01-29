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
 * @Description: 订单物流
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="order_physical_distribution对象", description="订单物流")
public class OrderPhysicalDistribution implements Serializable{
    
	/**订单物流id*/
    @ApiModelProperty(value = "订单物流id")
	private String orderPhysicalDistributionId;
	/**订单物流编码*/
    @ApiModelProperty(value = "订单物流编码")
	private String orderPhysicalDistributionCode;
	/**订单id*/
    @ApiModelProperty(value = "订单id")
	private String orderId;
    /** 快递公司id */
	@ApiModelProperty(value = "快递公司id")
	private String courierServicesCompanyId;
	/**发货人姓名*/
    @ApiModelProperty(value = "发货人姓名")
	private String startRealname;
	/**发货人手机号*/
    @ApiModelProperty(value = "发货人手机号")
	private String startPhone;
	/**发货人地址经纬度*/
	@ApiModelProperty(value = "发货人地址经纬度")
	private String startPositionLocation;
	/**发货人详细地址*/
    @ApiModelProperty(value = "发货人详细地址")
	private String startPositionDetail;
	/**收件人姓名*/
    @ApiModelProperty(value = "收件人姓名")
	private String endRealname;
	/**收件人手机号*/
    @ApiModelProperty(value = "收件人手机号")
	private String endPhone;
	/**收货人地址经纬度*/
	@ApiModelProperty(value = "收货人地址经纬度")
	private String endPositionLocation;
	/**收件人详细地址*/
    @ApiModelProperty(value = "收件人详细地址")
	private String endPositionDetail;
	/**快递员id*/
	@ApiModelProperty(value = "快递员id")
    private String courierId;
	/**sts*/
    @ApiModelProperty(value = "sts")
	private String sts;
	/**备注*/
    @ApiModelProperty(value = "备注")
	private String remark;
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
