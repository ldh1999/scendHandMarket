package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class InventoryRecommendModel extends Inventory implements Serializable {

    /** 置顶图片路径 */
    private String imageFirstPath;

    /** 商家名称 */
    private String merchantName;

    /** 该商品的偏向值 */
    private String preferencesSum;

}
