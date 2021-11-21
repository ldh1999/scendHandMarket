package com.ldh.inventoryService.model;


import com.ldh.inventoryService.pojo.Merchant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MerchantModel extends Merchant {

    private String stsName;
    private String authorityRealName;
}
