package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InventoryClientModel extends Inventory implements Serializable {

    private String title;
    private String key;
    private String content;
    private String inventoryCategory;
    private String inventoryCategoryName;
    private List<String> imageListUrl;
}
