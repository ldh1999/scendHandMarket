package com.ldh.inventoryService.model;

import com.ldh.inventoryService.pojo.ShopPreferencesType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ShopPreferencesTypeModel extends ShopPreferencesType implements Serializable {
}
