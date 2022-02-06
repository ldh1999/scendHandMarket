package com.ldh.modules.inventory.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.inventory.entity.InventoryComment;
import com.ldh.modules.inventory.model.InventoryCommentModel;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 商品评论
 * @Author: ldh
 * @Date:   2022-01-29
 * @Version: V1.0
 */
public interface InventoryCommentService extends IService<InventoryComment> {

    void sendComment(InventoryComment inventoryComment);

    IPage<InventoryCommentModel> getCommentList(Page page, QueryWrapper queryWrapper, String inventoryId, String fatherId) throws Exception;

    IPage<InventoryCommentModel> list(Page page, QueryWrapper queryWrapper,InventoryComment inventoryComment) throws Exception;

}
