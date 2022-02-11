package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.ShopTrolley;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Accessors(chain = true)
@Data
public class ShopTrolleyClientModel extends ShopTrolley implements Serializable {
    /** 商品名称 */
    private String inventoryName;
    /** 商家id */
    private String merchantId;
    /** 商家名称 */
    private String merchantName;
    /** 商品单价 */
    private Float inventoryPrice;
    /** 图片路径list */
    private List<String> imgPathList;
}
