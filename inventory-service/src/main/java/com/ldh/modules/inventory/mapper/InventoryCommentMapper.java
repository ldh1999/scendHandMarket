package com.ldh.modules.inventory.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.inventory.model.InventoryCommentModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 商品评论
 * @Author: jeecg-boot
 * @Date:   2022-01-29
 * @Version: V1.0
 */
@Mapper
public interface InventoryCommentMapper extends BaseMapper<InventoryComment> {

    IPage<InventoryCommentModel> getCommentList(Page page, QueryWrapper queryWrapper, String inventoryId, String fatherId);
}
