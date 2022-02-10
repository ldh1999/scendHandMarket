package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.Inventory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class InventoryCategoryClientModel extends Inventory implements Serializable {

    /** 置顶图片路径 */
    private String imageFirstPath;

    private String fatherName;
}
