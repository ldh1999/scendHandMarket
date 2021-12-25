package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.InventoryCategoryAssociate;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/inventory/inventoryCategoryAssociate/handle")
public interface InventoryCategoryAssociateClient {

    /** 商品分类关联列表 */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    Result<?> list(@SpringQueryMap InventoryCategoryAssociate inventoryCategoryAssociate,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order);

    /** 商品分类关联删除 */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(value = "id", required = true)String id);
}
