package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.mapper.InventoryCategoryMapper;
import com.ldh.modules.inventory.model.InventoryCategoryClientModel;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import common.OptionModel;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryCategoryServiceImpl extends ServiceImpl<InventoryCategoryMapper, InventoryCategory> implements InventoryCategoryService {

    @Autowired
    private InventoryCategoryMapper inventoryCategoryMapper;

    @Autowired
    private InventoryCategoryAssociateMapper inventoryCategoryAssociateMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthorityClient authorityClient;

    @Override
    public IPage<InventoryCategoryModel> list(Page page, QueryWrapper queryWrapper, InventoryCategory inventoryCategory) throws Exception {

        IPage<InventoryCategoryModel> iPage = inventoryCategoryMapper.list(page, queryWrapper, inventoryCategory);
        List<InventoryCategoryModel> list = iPage.getRecords();

        StringBuilder createIds = new StringBuilder();
        list.stream()
                .filter(e->e.getCreateBy() != null)
                .map(e-> e.getCreateBy()).distinct().collect(Collectors.toList())
                .forEach(e->{
                    createIds.append(e);
                    createIds.append(",");
                });

        Result<List<AuthorityInformationModel>> result = authorityClient.selectByIds(createIds.toString());
        if (result.isSuccess()){
            List<AuthorityInformationModel> authorityInformationModelLis = result.getResult();
            Map<String, String> map = authorityInformationModelLis.stream()
                    .collect(Collectors.toMap(AuthorityInformationModel::getAuthorityId,AuthorityInformationModel::getAuthorityName));
            list.forEach(e->{
                if (map.containsKey(e.getCreateBy())){
                    e.setCreateRealName(map.get(e.getCreateBy()));
                }
            });

        }else {
            throw new Exception("fegin error");
        }
        iPage.setRecords(list);
        return iPage;
    }

    @Override
    public void deleteAnyById(String id) {
        this.removeById(id);
        inventoryCategoryAssociateMapper.deleteByCategoryId(id);

    }

    @Override
    public List<OptionModel> getAllOption() {
        return inventoryCategoryMapper.getAllOption();
    }


    @Override
    public boolean setAllCategoryToRedis() {
        List<InventoryCategoryModel> list = inventoryCategoryMapper.getAllCategory();
        try{
            Map<String, Object> map = list.stream().collect(Collectors.toMap(InventoryCategoryModel::getId,r->r));
            redisTemplate.opsForValue().set("allInventoryCategoryList", map);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Map<String, InventoryCategoryModel> getAllCategoryToRedis() {

        if (!redisTemplate.hasKey("allInventoryCategoryList")){
            this.setAllCategoryToRedis();
        }else {
            List<InventoryCategoryModel> list = inventoryCategoryMapper.getAllCategory();
            Map<String, Object> map = list.stream().collect(Collectors.toMap(InventoryCategoryModel::getId,r->r));
            redisTemplate.opsForValue().set("allInventoryCategoryList", map);
        }
        return (Map<String, InventoryCategoryModel>) redisTemplate.opsForValue().get("allInventoryCategoryList");
    }

    @Override
    public String getCategoryIdsByInventoryId(String inventoryId) {
        return inventoryCategoryMapper.getCategoryIdsByInventoryId(inventoryId);
    }
}
