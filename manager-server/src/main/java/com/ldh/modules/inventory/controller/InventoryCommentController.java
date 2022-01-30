package com.ldh.modules.inventory.controller;

import com.ldh.inventoryService.pojo.InventoryComment;
import com.ldh.modules.inventory.service.InventoryCommentService;
import common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("inventory/comment")
public class InventoryCommentController {

    @Autowired
    private InventoryCommentService inventoryCommentService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "商品评论-全部评论", notes = "商品评论-全部评论")
    public Result<?> list(InventoryComment inventoryComment,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        return inventoryCommentService.list(inventoryComment, pageNo, pageSize, column, order);
    }

    @ApiOperation(value="商品评论-删除评论(伪删除)", notes="删除评论（伪删除）")
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(name = "id", required = true) String id){
        return inventoryCommentService.deleteById(id);
    }
}
