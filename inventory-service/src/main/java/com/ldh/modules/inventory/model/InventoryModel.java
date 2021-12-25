package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryModel extends Inventory implements Serializable {

    /** 店铺名称 */
    private String merchantName;


}
