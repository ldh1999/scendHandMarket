package com.ldh.modules.shop.model;

import com.ldh.modules.shop.entity.ShopPreferencesType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ShopPreferencesTypeModel extends ShopPreferencesType implements Serializable {
}
