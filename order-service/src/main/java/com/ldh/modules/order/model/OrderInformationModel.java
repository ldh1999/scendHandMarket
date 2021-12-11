package com.ldh.modules.order.model;

import com.ldh.modules.order.entity.OrderInformation;
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

}
