package com.ldh.modules.inventory.controller;
import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryVO;
import com.ldh.modules.inventory.model.InventoryVO;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("inventory/inventoryCategory")
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryService inventoryCategoryService;


    @ApiOperation(value="商品分类列表", notes="商品分类列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(InventoryCategory inventoryCategory,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<InventoryCategory> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            IPage<InventoryCategoryVO> iPage = inventoryCategoryService.list(page, queryWrapper, inventoryCategory);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="商品分类添加", notes="商品分类添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody InventoryCategory inventoryCategory, HttpServletRequest httpServletRequest){
        Result<?> result = new Result<>();
        try {

            HttpSession session = httpServletRequest.getSession();
            AuthorityInformation authorityInformation = (AuthorityInformation) session.getAttribute("user");
            if (authorityInformation!=null){
                inventoryCategory.setCreateBy(authorityInformation.getAuthorityId());
            }else {
                log.warn("该用户未登录");
            }
            inventoryCategoryService.save(inventoryCategory);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }



}
