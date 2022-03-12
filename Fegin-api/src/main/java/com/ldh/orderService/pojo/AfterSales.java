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
 * @Description: 售后
 * @Author: ldh
 * @Date:   2022-02-26
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="after_sales对象", description="售后")
public class AfterSales implements Serializable{
    
	/**afterSalesId*/
    @ApiModelProperty(value = "afterSalesId")
	private String afterSalesId;
	/**售后编码*/
	@ApiModelProperty(value = "售后编码")
	private String afterSalesNo;
	/**订单id*/
    @ApiModelProperty(value = "订单id")
	private String orderId;
	/**商品id*/
    @ApiModelProperty(value = "商品id")
	private String inventoryId;
	/**商家id*/
    @ApiModelProperty(value = "商家id")
	private String merchantId;
	/**售后情况 （wait_merchant等待商家）（end完成）(reject 商家拒绝)*/
    @ApiModelProperty(value = "售后情况 （wait_merchant等待商家）（end完成）(reject 商家拒绝)")
	private String afterSalesSts;
	/**外部快递编码（退货不需要）*/
    @ApiModelProperty(value = "外部快递编码（退货不需要）")
	private String expressCode;
	/**收货地址id（退货不需要）*/
    @ApiModelProperty(value = "收货地址id（退货不需要）")
	private String addressId;
	/**退换货原因*/
    @ApiModelProperty(value = "退换货原因")
	private Object reason;
	/**商家留言*/
    @ApiModelProperty(value = "商家留言")
	private Object merchantReason;
	/**退换货情况 （退back）( 换exchange )*/
    @ApiModelProperty(value = "退换货情况 （退back）( 换exchange )")
	private String inventoryMode;
	/**该售后情况（进行中 ing）(完成 end)（-1已删除）*/
    @ApiModelProperty(value = "该售后情况（进行中 ing）(完成 end)（-1已删除）")
	private String sts;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
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
