package com.ldh.inventoryService.model;

import com.ldh.inventoryService.pojo.Inventory;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryModel extends Inventory implements Serializable {
    /** 店铺名称 */
    private String merchantName;
}
