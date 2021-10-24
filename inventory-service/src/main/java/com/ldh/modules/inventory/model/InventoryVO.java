package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;

@Data
public class InventoryVO extends Inventory {
    /** 店铺名称 */
    private String merchantName;
}
