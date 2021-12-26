package com.ldh.orderService.model;

import com.ldh.orderService.pojo.OrderInformation;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Slf4j
public class OrderInformationModel extends OrderInformation implements Serializable {

    /** 商品名称 */
    private String inventoryName;
    /** 图片路径 */
    private String firstImagePath;

    /** 店铺编码 */
    private String merchantCode;
    /** 生成该订单的用户的username */
    private String userName;
    /** 商品编码 */
    private String inventoryCode;

}
