package com.ldh.modules.inventory.controller;


import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.constant.ShopPreferencesChange;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.entity.ShopTrolley;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.model.ShopTrolleyClientModel;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.modules.inventory.service.ShopTrolleyService;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import common.StringTo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("inventory/shopTrolley")
public class ShopTrolleyController {

    @Autowired
    private ShopTrolleyService shopTrolleyService;

    @Autowired
    private ShopPreferencesTypeService shopPreferencesTypeService;

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ShopPreferencesChange shopPreferencesChange;

    @ApiOperation(value="购物车list", notes="购物车list")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(ShopTrolley shopTrolley,
                          HttpServletRequest request,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage> result = new Result<>();
        Page<?> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        try{
            HttpSession session = request.getSession();
            AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            shopTrolley.setAuthorityId(authorityInformation.getAuthorityId());
            IPage<ShopTrolleyClientModel> iPage = shopTrolleyService.listToClient(page, queryWrapper, shopTrolley);
            result.setResult(iPage);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="添加购物车", notes="添加购物车")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> addShopTrolleyClient(@RequestBody ShopTrolley shopTrolley,
                                          HttpServletRequest request){
        Result<ShopTrolley> result = new Result<>();
        HttpSession session = request.getSession();
        try{
            AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            shopTrolley.setAuthorityId(authorityInformation.getAuthorityId());
            if (shopTrolleyService.countByUserIdAndInventoryId(authorityInformation.getAuthorityId(),shopTrolley.getInventoryId())>0){
                ShopTrolleyClientModel shopTrolleyClientModel = shopTrolleyService.selectByUserIdAndInventoryId(shopTrolley);
                shopTrolleyClientModel.setInventoryNum(shopTrolley.getInventoryNum()+shopTrolleyClientModel.getInventoryNum());
                shopTrolleyService.updateById(shopTrolleyClientModel);
            }else {
                shopTrolleyService.save(shopTrolley);
            }

            //记录偏好
            String categorys = inventoryCategoryService.getCategoryIdsByInventoryId(shopTrolley.getInventoryId());
            shopPreferencesTypeService.increasesValue(
                    categorys.split(","),
                    shopPreferencesChange.getJoinTrolley(),
                    authorityInformation.getAuthorityId());

            result.succcess("添加成功");
            result.setResult(shopTrolley);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="根据id修改", notes="根据id修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody ShopTrolley shopTrolley){
        Result result = new Result();
        try{
            shopTrolleyService.updateById(shopTrolley);
            result.setSuccess(true);
        }
        catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @ApiOperation(value="根据id删除", notes="根据id删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.GET)
    public Result<?> deleteById(@RequestParam(name = "id", required = true)String id){
        Result result = new Result();
        try{
            shopTrolleyService.removeById(id);
            result.succcess("删除成功");
        }
        catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private String getUserId(){
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        return authorityInformation.getAuthorityId();
    }

}
