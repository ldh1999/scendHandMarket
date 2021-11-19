package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.Inventory;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/inventory/inventory/")
public interface InventoryClient {
    @GetMapping("selectById")
    Result<Inventory> getById(@RequestParam(name = "id", required = true) String id);
}
