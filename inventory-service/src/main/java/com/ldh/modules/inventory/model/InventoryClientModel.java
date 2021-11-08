package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryClientModel extends Inventory implements Serializable {

    private String title;
    private String key;
    private String content;
    private String srcPath;
    private String inventoryCategory;
}
