package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.mapper.InventoryMapper;
import com.ldh.modules.inventory.model.InventoryVO;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.userService.client.MerchantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private MerchantClient merchantClient;

    @Override
    public IPage<InventoryVO> list(Page page, QueryWrapper queryWrapper, Inventory inventory) {

        IPage<InventoryVO> iPage = inventoryMapper.list(page, queryWrapper, inventory);
        List<InventoryVO> list = iPage.getRecords();
        list.stream().forEach(e->{
            e.setMerchantName(merchantClient.selectById(e.getId()).getResult().getMerchantName());
        });
        iPage.setRecords(list);

        return iPage;
    }
}
