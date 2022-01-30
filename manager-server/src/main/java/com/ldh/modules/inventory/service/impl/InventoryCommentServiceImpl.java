package com.ldh.modules.inventory.service.impl;

import com.ldh.inventoryService.client.InventoryCommentClient;
import com.ldh.inventoryService.pojo.InventoryComment;
import com.ldh.modules.inventory.service.InventoryCommentService;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommentServiceImpl implements InventoryCommentService {

    @Autowired
    private InventoryCommentClient inventoryCommentClient;

    @Override
    public Result<?> list(InventoryComment inventoryComment, Integer pageNo, Integer pageSize, String column, String order) {
        return inventoryCommentClient.list(inventoryComment, pageNo, pageSize, column, order);
    }

    @Override
    public Result<?> deleteById(String id) {
        return inventoryCommentClient.deleteById(id);
    }
}
