package com.ldh.modules.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantVO;
import com.ldh.modules.merchant.service.MerchantService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("merchant/examine")
@Api("商家")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(path = "list", method = RequestMethod.GET)
    public Result<?> list(Merchant merchant,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){

        Result result = new Result();
        Page page = new Page(pageNo,pageSize);
        QueryWrapper<?> queryWrapper =  new QueryWrapper<>();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            result.setResult(merchantService.list(page, queryWrapper, merchant));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商家审批管理修改", notes="商家审批管理修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody Merchant merchant){
        Result<?> result = new Result<>();
        try {
            merchantService.updateById(merchant);
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="根据id查询", notes="根据id查询")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(name = "id", required = true) String id){
        Result<MerchantVO> result = new Result<>();
        try {
            MerchantVO merchantVO = merchantService.selectById(id);
            result.setSuccess(true);
            result.setResult(merchantVO);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }



}
