package com.ldh.modules.merchant.model;

import com.ldh.modules.merchant.entity.Merchant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MerchantVO extends Merchant {

    private String stsName;
    private String authorityRealName;
}
