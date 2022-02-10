package com.ldh.inventoryService.model;

import com.ldh.inventoryService.pojo.InventoryCategory;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryCategoryModel extends InventoryCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String createRealName;

    private String fatherName;

    private Integer sonNum;
}
