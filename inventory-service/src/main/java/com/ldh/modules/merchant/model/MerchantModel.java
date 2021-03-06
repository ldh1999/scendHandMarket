package com.ldh.modules.merchant.model;

import com.ldh.modules.merchant.entity.Merchant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class MerchantModel extends Merchant implements Serializable {

    private String stsName;
    private String authorityRealName;
}
