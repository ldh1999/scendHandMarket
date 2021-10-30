package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.mapper.InventoryCategoryMapper;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.userService.client.AuthorityClient;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryCategoryServiceImpl extends ServiceImpl<InventoryCategoryMapper, InventoryCategory> implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryMapper inventoryCategoryMapper;

    @Autowired
    private AuthorityClient authorityClient;

    @Override
    public IPage<InventoryCategoryModel> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory) {


        IPage<InventoryCategoryModel> iPage = inventoryCategoryMapper.list(page, queryWrapper, inventoryCategory);
        List<InventoryCategoryModel> list = iPage.getRecords();
        Map<String, String> map = new HashMap<>();
        list.stream().forEach(e->{
            String create = e.getCreateBy();
            if (map.containsKey(create)){
                e.setCreateRealName(map.get(create));
            }else {
                String createRealName = authorityClient.selectById(e.getCreateBy()).getResult().getRealName();
                e.setCreateRealName(createRealName);
                map.put(create, createRealName);
            }
        });
        iPage.setRecords(list);
        return iPage;
    }
}
