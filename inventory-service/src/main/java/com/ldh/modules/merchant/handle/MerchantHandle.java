package com.ldh.modules.merchant.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantModel;
import com.ldh.modules.merchant.service.MerchantService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("merchant/handle")
@Api("商家对外接口")
public class MerchantHandle {

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
        Result<Merchant> result = new Result<>();
        try {
            merchantService.updateById(merchant);
            result.setResult(merchant);
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
        Result<MerchantModel> result = new Result<>();
        try {
            MerchantModel merchantModel = merchantService.selectById(id);
            result.setSuccess(true);
            result.setResult(merchantModel);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="根据许多id查询", notes="根据许多id查询")
    @RequestMapping(path = "/selectByIds", method = RequestMethod.GET)
    public Result<?> selectByIds(@RequestParam(name = "ids", required = true) String ids){
        Result<List<MerchantModel>> result = new Result<>();
        try {
            result.setResult(merchantService.selectByIds(ids.split(",")));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    /**
     * 根据条件获取商家数量
     *
     * @param obj
     * @return
     */
    @ApiOperation(value = "订单信息表-根据条件获取商家数量", notes = "订单信息表-根据条件获取商家数量")
    @GetMapping(value = "/getOrderCountByObject")
    public Result<?> getMerchantCountByObject(@RequestParam(name = "id", required = false) String obj) {
        return Result.OK(merchantService.getMerchantCountByObject(obj));
    }


}
