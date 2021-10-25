package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.mapper.InventoryCategoryMapper;
import com.ldh.modules.inventory.model.InventoryCategoryVO;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryCategoryServiceImpl extends ServiceImpl<InventoryCategoryMapper, InventoryCategory> implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryMapper inventoryCategoryMapper;

    @Override
    public IPage<InventoryCategoryVO> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory) {
        return inventoryCategoryMapper.list(page, queryWrapper, inventoryCategory);
    }
}
