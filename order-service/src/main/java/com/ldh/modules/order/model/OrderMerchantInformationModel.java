package com.ldh.modules.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ldh.modules.order.entity.OrderInformation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Slf4j
public class OrderMerchantInformationModel extends OrderInformation implements Serializable {


    public OrderMerchantInformationModel() {
    }

    public OrderMerchantInformationModel(OrderInformation orderInformation) {
        super(orderInformation);
    }

    /**用户id*/
    @ApiModelProperty(value = "用户id")
    private String authorityId;
    /**通讯人姓名*/
    @ApiModelProperty(value = "通讯人姓名")
    private String addressName;
    /**通讯人地址*/
    @ApiModelProperty(value = "通讯人地址")
    private String addressSite;
    /**通讯人手机号*/
    @ApiModelProperty(value = "通讯人手机号")
    private String addressPhone;

    /** 商品名称 */
    private String inventoryName;

}
