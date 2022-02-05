package com.ldh.modules.inventory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.model.InventoryModel;
import com.ldh.modules.inventory.model.SearchResponseModel;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.service.MerchantService;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("inventory/search")
public class InventorySearchController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MerchantService merchantService;

    /**
     * 自动搜索下拉框
     * @param str
     * @return
     */
    @GetMapping("autoSearch")
    public Result<?> autoSearch(@RequestParam("str") String str){
        Result result = new Result();
        try {
            result.setResult(inventoryService.autoSearch(str));
            result.setSuccess(true);
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }


    @GetMapping("getSearchList")
    public Result<?> getSearchList(@RequestParam("str") String str,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        Result result = new Result();
        SearchResponseModel searchResponseModel = new SearchResponseModel();
        Page<InventoryModel> page = new Page<>(pageNo, pageSize);
        Page<Merchant> merchantPage = new Page<>(1,10);
        QueryWrapper queryWrapper = new QueryWrapper();
        Merchant merchant = new Merchant();
        merchant.setMerchantName(str);
        Inventory inventory = new Inventory();
        inventory.setInventoryName(str);
        try {
            searchResponseModel.setInventoryResult(inventoryService.listToClient(page, queryWrapper, inventory));
            searchResponseModel.setMerchantResult(merchantService.list(merchantPage, new QueryWrapper(), merchant));
            result.setResult(searchResponseModel);
            result.setSuccess(true);
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
