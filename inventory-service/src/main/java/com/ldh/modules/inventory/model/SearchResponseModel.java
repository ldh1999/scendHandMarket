package com.ldh.modules.inventory.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ldh.modules.merchant.model.MerchantModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class SearchResponseModel implements Serializable {

    private IPage<InventoryClientModel> inventoryResult;

    private IPage<MerchantModel> merchantResult;
}
