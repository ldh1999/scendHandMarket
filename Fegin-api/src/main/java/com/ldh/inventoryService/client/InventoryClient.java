package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.Inventory;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
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

    /** 商品列表 */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap Inventory inventory,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "column", required = false) String column,
                          @RequestParam(name = "order", required = false) String order);

    /** 商品删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true) String id);
}
