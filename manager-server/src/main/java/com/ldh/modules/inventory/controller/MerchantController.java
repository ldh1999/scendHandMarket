package com.ldh.modules.inventory.controller;

import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.inventoryService.pojo.Merchant;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("merchant/examine")
@Api("商家")
public class MerchantController {

    @Autowired
    private MerchantClient merchantClient;

    @RequestMapping(path = "list", method = RequestMethod.GET)
    public Result<?> list(Merchant merchant,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){

        return merchantClient.list(merchant, pageNo, pageSize, column, order);
    }

    @ApiOperation(value="商家审批管理修改", notes="商家审批管理修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody Merchant merchant){
        return merchantClient.updateById(merchant);
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(name = "id", required = true) String id){
      return merchantClient.selectById(id);
    }

}
