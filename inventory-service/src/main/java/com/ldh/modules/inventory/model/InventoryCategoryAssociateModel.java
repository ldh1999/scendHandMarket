package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryCategoryAssociateModel extends InventoryCategoryAssociate implements Serializable {
    private String cateName;

    private String fatherCateName;
}
