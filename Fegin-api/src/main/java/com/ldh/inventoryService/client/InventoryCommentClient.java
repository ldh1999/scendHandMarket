package com.ldh.inventoryService.client;

import com.ldh.inventoryService.pojo.InventoryComment;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("inventoryservice/inventory/inventoryComment/handle")
public interface InventoryCommentClient {

    /**
     * 商品评论-全部评论
     * @param inventoryComment
     * @param pageNo
     * @param pageSize
     * @param column
     * @param order
     * @return
     */
    @GetMapping("list")
    Result<?> list(@SpringQueryMap InventoryComment inventoryComment,
                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                   @RequestParam(name="column", required = false) String column,
                   @RequestParam(name="order", required = false) String order);

    /**
     * 商品评论-删除评论(伪删除)
     * @param id
     * @return
     */
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    Result<?> deleteById(@RequestParam(name = "id", required = true) String id);
}
