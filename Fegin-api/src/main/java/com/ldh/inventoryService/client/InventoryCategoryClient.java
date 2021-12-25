package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.InventoryCategory;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/inventory/inventoryCategory/handle")
public interface InventoryCategoryClient {

    /** 商品分类列表 */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap InventoryCategory inventoryCategory,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /** 商品分类添加 */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    Result<?> insert(@RequestBody InventoryCategory inventoryCategory);

    /** 商品分类修改 */
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    Result<?> update(@RequestBody InventoryCategory inventoryCategory);

    /** 商品分类删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);
}
