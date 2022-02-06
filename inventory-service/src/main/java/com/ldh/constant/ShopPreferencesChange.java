package com.ldh.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("ShopPreferencesChange")
public class ShopPreferencesChange {
    @Value("${preferences.look}")
    private Float look;
    private Float buy;
    @Value("${preferences.joinTrolley}")
    private Float joinTrolley;
}
