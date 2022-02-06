/*
package com.ldh.inventoryService.client;

import com.ldh.inventoryService.model.ShopPreferencesTypeModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/inventory/shopPreferencesType/handle")
public interface ShopPreferencesTypeClient {

    */
/**
     * 用户进行操作时 偏好值进行改变
     * @param typeId
     * @return
     *//*

    @GetMapping("operation")
    Result<?> look(@RequestParam("typeId") String typeId, String operation);

    */
/**
     * 获取该用户的偏好列表
     * @return
     *//*

    @GetMapping("getList")
    Result<ShopPreferencesTypeModel> joinTrolley();
}
*/
