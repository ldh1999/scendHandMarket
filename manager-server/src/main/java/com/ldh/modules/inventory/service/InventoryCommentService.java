package com.ldh.modules.inventory.service;

import com.ldh.inventoryService.pojo.InventoryComment;
import common.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface InventoryCommentService {

    /**
     * 商品评论-全部评论
     * @param inventoryComment
     * @param pageNo
     * @param pageSize
     * @param column
     * @param order
     * @return
     */
    Result<?> list(InventoryComment inventoryComment,
                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                   @RequestParam(name="column", required = false) String column,
                   @RequestParam(name="order", required = false) String order);


    /**
     * 商品评论-删除评论(伪删除)
     * @param id
     * @return
     */
    Result<?> deleteById(@RequestParam(name = "id", required = true) String id);
}
