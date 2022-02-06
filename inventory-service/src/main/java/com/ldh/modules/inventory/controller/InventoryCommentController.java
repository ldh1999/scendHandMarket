package com.ldh.modules.inventory.controller;

import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryComment;
import com.ldh.modules.inventory.model.InventoryCommentModel;
import com.ldh.modules.inventory.service.InventoryCommentService;
import common.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 商品评论
 * @Author: ldh
 * @Date: 2022-01-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "商品评论")
@RestController
@RequestMapping("/inventory/inventoryComment")
public class InventoryCommentController {
    @Autowired
    private InventoryCommentService inventoryCommentService;


    @GetMapping("listClient")
    @ApiOperation(value = "商品评论-获取评论", notes = "商品评论-获取评论")
    public Result<?> listClient(@RequestParam(name = "inventoryId", required = true) String inventoryId,
                                @RequestParam(name = "fatherId", defaultValue = "-1") String fatherId,
                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        Result<IPage> result = new Result<>();
        Page<InventoryCommentModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        try {
            IPage<InventoryCommentModel> iPage = inventoryCommentService.getCommentList(page, queryWrapper, inventoryId, fatherId);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;

    }


    /**
     * 发送评论
     *
     * @return
     */
    @PostMapping("sendComment")
    @ApiOperation(value = "商品评论-发送评论", notes = "商品评论-发送评论")
    public Result<?> sendComment(@RequestBody InventoryComment inventoryComment) {
        Result result = new Result();
        try {
            inventoryCommentService.sendComment(inventoryComment);
            result.succcess("发送成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

}
