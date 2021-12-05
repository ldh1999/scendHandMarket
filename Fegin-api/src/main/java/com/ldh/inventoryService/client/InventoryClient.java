package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.Inventory;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("inventoryservice/inventory/handle/")
public interface InventoryClient {
    @GetMapping("selectById")
    Result<Inventory> getById(@RequestParam(name = "id", required = true) String id);

    @RequestMapping(path = "/selectByIds", method = RequestMethod.GET)
    Result<List<Inventory>> selectByIds(@RequestParam(name = "ids", required = true)String ids);
}
