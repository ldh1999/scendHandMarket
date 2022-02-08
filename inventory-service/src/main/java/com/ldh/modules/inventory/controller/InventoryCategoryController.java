package com.ldh.modules.inventory.controller;
import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategory;
import com.ldh.modules.inventory.model.InventoryCategoryClientModel;
import com.ldh.modules.inventory.model.InventoryCategoryModel;
import com.ldh.modules.inventory.model.InventoryRecommendModel;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.util.RedisSessionUtil;
import common.OptionModel;
import common.Result;
import common.StringTo;
import constant.UploadFileConstant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("inventory/inventoryCategory")
public class InventoryCategoryController {

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

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
            IPage<InventoryCategoryModel> iPage = inventoryCategoryService.list(page, queryWrapper, inventoryCategory);
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
            AuthorityInformation authorityInformation = (AuthorityInformation)RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            if (authorityInformation!=null){
                inventoryCategory.setCreateBy(authorityInformation.getAuthorityId());
            }else {
                log.warn("该用户未登录");
            }
            inventoryCategoryService.save(inventoryCategory);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("增加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error("该用户未登录");
        }
        return result;
    }

    @ApiOperation(value="商品分类修改", notes="商品分类修改")
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Result<?> update(@RequestBody InventoryCategory inventoryCategory, HttpServletRequest httpServletRequest){
        Result<?> result = new Result<>();
        try {
            HttpSession session = httpServletRequest.getSession();
            AuthorityInformation authorityInformation = (AuthorityInformation)RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            if (authorityInformation!=null){
                inventoryCategory.setCreateBy(authorityInformation.getAuthorityId());
            }else {
                log.warn("该用户未登录");
            }
            inventoryCategoryService.updateById(inventoryCategory);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }


    @ApiOperation(value="商品分类删除", notes="商品分类删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            //TODO
            inventoryCategoryService.deleteAnyById(id);
            inventoryCategoryService.setAllCategoryToRedis();
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value="获取所有商品分类", notes="获取所有商品分类")
    @RequestMapping(path = "/getAllOption", method = RequestMethod.GET)
    public Result<List<OptionModel>> getAllOption(){
        Result<List<OptionModel>> result = new Result<>();
        try{
            result.setResult(inventoryCategoryService.getAllOption());
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="根据id获取该分类信息", notes="根据id获取该分类信息")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(name = "id", required = true)String id){
        Result<InventoryCategory> result = new Result<>();
        try{
            result.setResult(inventoryCategoryService.getById(id));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }


    @ApiOperation(value = "分类信息展示", notes = "分类信息展示")
    @RequestMapping(path = "/listClientToCategory", method = RequestMethod.GET)
    public Result<?> listClientToRecommend(@RequestParam(name = "categoryId", required = true) String categoryId,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "12") Integer pageSize
    ) {
        Result<IPage> result = new Result<>();
        Page<Inventory> page = new Page<>(pageNo, pageSize);
        //TODO
        try {
            IPage<InventoryCategoryClientModel> iPage = inventoryService.listToClientByCategory(page, null, categoryId);
            List<InventoryCategoryClientModel> list = iPage.getRecords();
            List<String> idList = new LinkedList<>();
            //构建请求参数
            list.stream().forEach(e->{
                idList.add(e.getId());
            });
            ImageListGetVO imageListGetVO = new ImageListGetVO();
            imageListGetVO
                    .setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                    .setObjectId(idList);
            Result<List<ImageNoteModel>> feginResult = imageNoteGetClient.getByObjectIdAndImgGroupList(imageListGetVO);
            //获取请求返回list
            List<ImageNoteModel> imageNoteModels = feginResult.getResult();
            if (imageNoteModels != null){
                //构建针对id的对象map
                Map<String, List<ImageNoteModel>> map = new HashMap<>();
                imageNoteModels.stream().forEach(e->{
                    if (!map.containsKey(e.getObjectId())){
                        List<ImageNoteModel> list1 = new LinkedList<>();
                        list1.add(e);
                        map.put(e.getObjectId(),list1);
                    }else {
                        map.get(e.getObjectId()).add(e);
                    }
                });
                //将排序值最小的图片加入到list的firstimg中
                list.stream().forEach(e->{
                    List<ImageNoteModel> modelList =  map.get(e.getId());
                    if (modelList != null){
                        int min = 0;
                        int current = 0;
                        for (ImageNoteModel model : modelList){
                            if (model.getSort() < min){
                                current = min;
                            }
                            current++;
                        }
                        e.setImageFirstPath(modelList.get(min).getImgPath());
                    }
                });
            }
            iPage.setRecords(list);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }



}
