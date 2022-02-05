package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InventoryClientModel extends Inventory implements Serializable {

    public InventoryClientModel() {
    }

    public InventoryClientModel(Inventory inventory) {
        super(inventory);
    }

    private String title;
    private String key;
    private String content;
    private String inventoryCategory;
    private String inventoryCategoryName;
    private String firstImagePath;
    /** 商家名称 */
    private String merchantName;
    private List<String> imageListUrl;
    private String merchantImgPath;


}
