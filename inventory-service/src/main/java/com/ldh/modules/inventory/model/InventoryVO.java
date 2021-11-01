package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class InventoryVO extends Inventory implements Serializable {

    /** 商品类别 */
    private String[] inventoryCategory;
}
