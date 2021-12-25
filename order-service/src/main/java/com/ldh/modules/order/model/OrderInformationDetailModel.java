package com.ldh.modules.order.model;

import com.ldh.modules.order.entity.OrderInformation;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Slf4j
public class OrderInformationDetailModel extends OrderInformation implements Serializable {

    public OrderInformationDetailModel(OrderInformation orderInformation) {
        super(orderInformation);
    }

    /** 图片路径 */
    private String firstImagePath;

    /** 店铺编码 */
    private String merchantCode;
    /** 店铺名 */
    private String merchantName;
    /** 店铺介绍 */
    private String merchantInformation;
    /** 备案电话 */
    private String recordPhone;
    /** 备案身份证 */
    private String recordIdentityCode;
    /** 备案真实姓名 */
    private String recordRealName;
    /** 店铺状态 */
    private String merchantSts;




    /** 生成该订单的用户的username */
    private String userName;
    /**用户昵称*/
    private String authorityName;
    /**真实名称*/
    private String realName;
    /** 手机号 */
    private String phone;



    /** 商品编码 */
    private String inventoryCode;
    /** 商品名称 */
    private String inventoryName;
    /** 商品信息 */
    private String inventoryInformation;
    /** 商品单价 */
    private Float inventoryPrice;
}
