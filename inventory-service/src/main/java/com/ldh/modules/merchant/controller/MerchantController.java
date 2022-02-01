package com.ldh.modules.merchant.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.constant.FilePath;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantModel;
import com.ldh.modules.merchant.service.CosImageService;
import com.ldh.modules.merchant.service.MerchantService;
import common.Result;
import common.StringTo;
import constant.UploadFileConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Random;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("merchant/examine")
@Api("商家")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CosImageService cosImageService;

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

    @ApiOperation(value="根据用户id查询", notes="根据用户id查询")
    @RequestMapping(path = "/selectByUserId", method = RequestMethod.GET)
    public Result<?> selectByUserId(@RequestParam(name = "id", required = true) String id){
        Result<MerchantModel> result = new Result<>();
        try {
            MerchantModel merchantModel = merchantService.selectByUserId(id);
            result.setSuccess(true);
            result.setResult(merchantModel);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="增加", notes="增加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody Merchant merchant){
        Result<MerchantModel> result = new Result<>();
        try {
            merchant.setMerchantCode(UUID.randomUUID().toString());
            merchantService.save(merchant);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="头像更新", notes="头像更新")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(name = "id") String id
    ){
        Result<String> result = new Result<>();
        try{
            String saveDbPath = cosImageService.uploadImage(file);
            Merchant merchant = new Merchant();
            merchant.setMerchantId(id);
            merchant.setImgPath(saveDbPath);
            merchantService.updateById(merchant);
            result.setResult(saveDbPath);
            result.setMessage("上传成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.error("上传失败");
        }
        return result;
    }



}
