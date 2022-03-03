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
 * @Description: 订单信息表
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Data
@ApiModel(value="order_information对象", description="订单信息表")
public class OrderInformation implements Serializable{
	public OrderInformation() {
	}

	/**
	 * 深拷贝
	 */
	public OrderInformation(OrderInformation orderInformation) {
		this.orderId = orderInformation.orderId;
		this.orderCode = orderInformation.orderCode;
		this.addressId = orderInformation.addressId;
		this.merchantId = orderInformation.merchantId;
		this.inventoryId = orderInformation.inventoryId;
		this.orderInventoryNum = orderInformation.orderInventoryNum;
		this.orderInventoryPrice = orderInformation.orderInventoryPrice;
		this.orderInventoryPriceSum = orderInformation.orderInventoryPriceSum;
		this.isAfterSales = orderInformation.isAfterSales;
		this.createTime = orderInformation.createTime;
		this.createBy = orderInformation.createBy;
		this.updateBy = orderInformation.updateBy;
		this.updateTime = orderInformation.updateTime;
		this.remark = orderInformation.remark;
		this.sts = orderInformation.sts;
	}

	/**订单id*/
    @ApiModelProperty(value = "订单id")
	private String orderId;
	/**订单编号*/
    @ApiModelProperty(value = "订单编号")
	private String orderCode;
	/**地址id*/
    @ApiModelProperty(value = "地址id")
	private String addressId;
	/**商家id*/
    @ApiModelProperty(value = "商家id")
	private String merchantId;
	/**商品id*/
    @ApiModelProperty(value = "商品id")
	private String inventoryId;
	/**该订单商品数量*/
    @ApiModelProperty(value = "该订单商品数量")
	private Integer orderInventoryNum;
	/**该订单商品单价*/
	@ApiModelProperty(value = "该订单商品单价")
	private Float orderInventoryPrice;
	/**该订单价格*/
    @ApiModelProperty(value = "该订单价格")
	private Float orderInventoryPriceSum;
	/**是否售后*/
	@ApiModelProperty(value = "是否售后")
	private String isAfterSales;
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
	/**订单备注*/
    @ApiModelProperty(value = "订单备注")
	private Object remark;
	/**订单状态 0：待接单，1：待发货，2：结单，3：已发货 4:待支付	*/
    @ApiModelProperty(value = "订单状态 0：待接单，1：待发货，2：结单，3：已发货	 4:待支付")
	private String sts;
}
