package com.ldh.inventoryService.model;

import com.ldh.inventoryService.pojo.InventoryCategoryAssociate;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryCategoryAssociateModel extends InventoryCategoryAssociate implements Serializable {
    private String cateName;

    private String fatherCateName;
}
