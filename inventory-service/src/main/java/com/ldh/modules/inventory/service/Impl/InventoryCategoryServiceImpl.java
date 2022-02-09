package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.mapper.InventoryCategoryMapper;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import com.ldh.userService.client.AuthorityClient;
import com.ldh.userService.model.AuthorityInformationModel;
import common.OptionModel;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ShopPreferencesTypeService shopPreferencesTypeService;

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
                    .collect(Collectors.toMap(AuthorityInformationModel::getAuthorityId,AuthorityInformationModel::getRealName));
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
    @Transactional
    public void deleteAnyById(String id) {
        //删自己
        this.removeById(id);
        //如果我是父级则把子集删了 （如果不是反也删个寂寞）
        inventoryCategoryMapper.deleteByFatherId(id);

        inventoryCategoryAssociateMapper.deleteByCategoryId(id);
        inventoryCategoryAssociateMapper.deleteByCategoryFatherId(id);

        shopPreferencesTypeService.deleteByTypeId(id);
    }

    @Override
    public List<OptionModel> getAllOption() {
        return inventoryCategoryMapper.getAllOption(null);
    }

    @Override
    public List<OptionModel> getOptionByFatherId(String fatherId) {
        return inventoryCategoryMapper.getOptionByFatherId(fatherId);
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
    public String getFatherCategoryIdsByInventoryId(String inventoryId) {
        return inventoryCategoryMapper.getFatherCategoryIdsByInventoryId(inventoryId);
    }

    @Override
    public List<OptionModel> getAllOptionByFatherId(String fatherId) {
        return inventoryCategoryMapper.getAllOption(fatherId);
    }
}
