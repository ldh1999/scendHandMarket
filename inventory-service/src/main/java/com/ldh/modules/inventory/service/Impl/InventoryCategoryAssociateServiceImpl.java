package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.model.InventoryCategoryAssociateModel;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryCategoryAssociateServiceImpl extends ServiceImpl<InventoryCategoryAssociateMapper, InventoryCategoryAssociate> implements InventoryCategoryAssociateService {


    @Autowired
    private InventoryCategoryAssociateMapper inventoryCategoryAssociateMapper;

    @Override
    public IPage<InventoryCategoryAssociateModel> list(Page page, QueryWrapper queryWrapper, InventoryCategoryAssociate inventoryCategoryAssociate) {
        return inventoryCategoryAssociateMapper.list(page, queryWrapper, inventoryCategoryAssociate);
    }

    @Override
    public Integer deleteByCategoryId(String categoryId) {
        return inventoryCategoryAssociateMapper.deleteByCategoryId(categoryId);
    }

    @Override
    public Integer deleteByInventoryId(String inventoryId) {
        return inventoryCategoryAssociateMapper.deleteByInventoryId(inventoryId);
    }

    @Override
    public List<InventoryCategoryAssociateModel> getByInventoryId(String id) {
        return inventoryCategoryAssociateMapper.getByInventoryId(id);
    }
}
