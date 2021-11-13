package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.InventoryCategory;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryCategoryModel extends InventoryCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String createRealName;
}
