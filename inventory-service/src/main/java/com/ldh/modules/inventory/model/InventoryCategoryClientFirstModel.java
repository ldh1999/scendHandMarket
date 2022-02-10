package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.InventoryCategory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class InventoryCategoryClientFirstModel implements Serializable {
    /** 全部类别 */
    private List<InventoryCategoryModel> categoryAll;
    /** 全部大类 */
    private List<InventoryCategoryModel> categoryFather;
    /** key大类id    value小类 */
    private Map<String,List> categoryMap;
}
