package com.ldh.modules.order.entity;

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
 * @Description: 订单信息表
 * @Date:   2021-11-28
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="order_information对象", description="订单信息表")
public class OrderInformation implements Serializable{
    
	/**订单id*/
    @ApiModelProperty(value = "订单id")
	@TableId(type = IdType.UUID)
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
	/**创建时间*/
	@TableField(fill = FieldFill.INSERT)
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
	@TableField(fill = FieldFill.UPDATE)
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
