package com.ldh.modules.inventory.controller;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.model.InventoryCategoryAssociateModel;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@Api("商品分类关联")
@RequestMapping("inventory/inventoryCategoryAssociate")
public class InventoryCategoryAssociateController {

    @Autowired
    private InventoryCategoryAssociateService inventoryCategoryAssociateService;

    @ApiOperation(value="商品分类关联列表", notes="商品分类关联列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(InventoryCategoryAssociate inventoryCategoryAssociate,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<InventoryCategoryAssociate> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<InventoryCategoryAssociateModel> iPage = inventoryCategoryAssociateService.list(page, queryWrapper, inventoryCategoryAssociate);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品分类关联添加", notes="商品分类关联添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody InventoryCategoryAssociate inventoryCategoryAssociate, HttpServletRequest httpServletRequest){
        Result<?> result = new Result<>();
        try {
            HttpSession session = httpServletRequest.getSession();
            AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            if (authorityInformation!=null){
                inventoryCategoryAssociate.setCreateBy(authorityInformation.getAuthorityId());
            }else {
                log.warn("该用户未登录");
            }
            inventoryCategoryAssociateService.save(inventoryCategoryAssociate);
            result.succcess("增加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="根据商品id查找", notes="根据商品id查找")
    @RequestMapping(path = "/getByInventoryId", method = RequestMethod.DELETE)
    public Result<?> getByInventoryId(@RequestParam(value = "id", required = true)String id){

        Result<List<?>> result = new Result<>();
        try{
            result.setResult(inventoryCategoryAssociateService.getByInventoryId(id));
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品分类关联删除", notes="商品分类关联删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            inventoryCategoryAssociateService.removeById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }
}
