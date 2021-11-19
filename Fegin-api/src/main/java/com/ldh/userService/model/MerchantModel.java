package com.ldh.userService.model;


import com.ldh.userService.pojo.Merchant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MerchantModel extends Merchant {

    private String stsName;
    private String authorityRealName;
}
