package com.ldh.modules.inventory.controller;


import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.constant.ShopPreferencesChange;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.entity.InventoryCategoryAssociate;
import com.ldh.modules.inventory.model.*;
import com.ldh.modules.inventory.service.InventoryCategoryAssociateService;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import com.ldh.otherResourceService.client.ImageNoteClient;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.FileNoteVO;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.otherResourceService.model.ImageNoteVO;
import com.ldh.util.RedisSessionUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import common.InitUploadModel;
import common.Result;
import common.StringTo;
import constant.UploadFileConstant;
import constant.UserOperationConstant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("inventory/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryCategoryAssociateService inventoryCategoryAssociateService;

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @Autowired
    private ImageNoteClient imageNoteClient;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Autowired
    private ShopPreferencesTypeService shopPreferencesTypeService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ShopPreferencesChange shopPreferencesChange;


    @ApiOperation(value = "商品列表", notes = "商品列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(Inventory inventory,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "column", required = false) String column,
                          @RequestParam(name = "order", required = false) String order) {
        Result<IPage> result = new Result<>();
        Page<InventoryModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (order != null) {
            if (order.equals("desc")) {
                queryWrapper.orderByDesc(StringTo.humpToLine(column));
            } else {
                queryWrapper.orderByAsc(StringTo.humpToLine(column));
            }
        }
        try {
            IPage<InventoryModel> iPage = inventoryService.list(page, queryWrapper, inventory);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value = "主页商品呈现（暂时 ：推荐/随机）", notes = "主页商品呈现（暂时 ：推荐/随机）")
    @RequestMapping(path = "/listClientToRecommend", method = RequestMethod.GET)
    public Result<?> listClientToRecommend(Inventory inventory,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "12") Integer pageSize,
                                           @RequestParam(name = "operation", required = false) String operation
    ) {
        Result<IPage> result = new Result<>();
        Page<Inventory> page = new Page<>(pageNo, pageSize);
        try {
            IPage<InventoryRecommendModel> iPage = null;
            if ("recommend".equals(operation)){
                iPage = inventoryService.getRecommendList(page);
            }else if ("rand".equals(operation)){
                iPage = inventoryService.getRandList(page);
            }else {
                throw new Exception("operation  not find");
            }
            //获取全部推荐商品
            List<InventoryRecommendModel> list = iPage.getRecords();
            List<String> idList = new LinkedList<>();
            //构建请求参数
            list.stream().forEach(e -> {
                idList.add(e.getId());
            });
            ImageListGetVO imageListGetVO = new ImageListGetVO();
            imageListGetVO
                    .setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                    .setObjectId(idList);
            Result<List<ImageNoteModel>> feginResult = imageNoteGetClient.getByObjectIdAndImgGroupList(imageListGetVO);
            //获取请求返回list
            List<ImageNoteModel> imageNoteModels = feginResult.getResult();
            //构建针对id的对象map
            Map<String, List<ImageNoteModel>> map = new HashMap<>();
            imageNoteModels.stream().forEach(e -> {
                if (!map.containsKey(e.getObjectId())) {
                    List<ImageNoteModel> list1 = new LinkedList<>();
                    list1.add(e);
                    map.put(e.getObjectId(), list1);
                } else {
                    map.get(e.getObjectId()).add(e);
                }
            });
            //将排序值最小的图片加入到list的firstimg中
            list.stream().forEach(e -> {
                List<ImageNoteModel> modelList = map.get(e.getId());
                if (modelList != null) {
                    int min = 0;
                    int current = 0;
                    for (ImageNoteModel model : modelList) {
                        if (model.getSort() < min) {
                            current = min;
                        }
                        current++;
                    }
                    e.setImageFirstPath(modelList.get(min).getImgPath());
                }
            });
            iPage.setRecords(list);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "商品删除", notes = "商品删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> deleteById(@RequestParam(value = "id", required = true) String id) {

        Result<?> result = new Result<>();
        try {
            //TODO 暂时此接口针对客户端  做伪删除
//            inventoryService.deleteAnyById(id);
            Inventory inventory = new Inventory();
            inventory.setId(id);
            inventory.setSts("-1");
            inventoryService.updateById(inventory);
            result.succcess("删除成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }

    @ApiOperation(value = "用户添加商品", notes = "用户添加商品")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody InventoryVO inventoryVO) {

        Result<Inventory> result = new Result<>();
        String[] category = inventoryVO.getInventoryCategory();
        Inventory inventory = inventoryVO;

        try {
            inventory.setInventoryCode(UUID.randomUUID().toString());
            inventoryService.save(inventory);
            /*Arrays.stream(category).forEach(e -> {
                InventoryCategoryAssociate inventoryCategoryAssociate = new InventoryCategoryAssociate();
                inventoryCategoryAssociate.setInventoryCategoryId(e).
                        setInventoryId(inventoryVO.getId());
                inventoryCategoryAssociateService.save(inventoryCategoryAssociate);
            });*/
            result.succcess("添加成功");
            result.setResult(inventory);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @ApiOperation(value = "用户修改商品", notes = "用户修改商品")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody InventoryVO inventoryVO) {

        Result<?> result = new Result<>();
        String[] category = inventoryVO.getInventoryCategory();
        Inventory inventory = inventoryVO;
        try {
            inventoryService.updateById(inventory);
            /*List<InventoryCategoryAssociateModel> list = inventoryCategoryAssociateService.getByInventoryId(inventory.getId());
            Map mapNew = Arrays.stream(category).collect(Collectors.toMap(String::toString, r -> r));
            list.stream().forEach(e -> {
                if (!mapNew.containsKey(e.getInventoryCategoryId())) {
                    inventoryCategoryAssociateService.removeById(e.getId());
                }
            });

            Map mapOld = list.stream().collect(Collectors.toMap(InventoryCategoryAssociate::getInventoryCategoryId, r -> r));
            Arrays.stream(category).forEach(e -> {
                if (!mapOld.containsKey(e.toString())) {
                    InventoryCategoryAssociate inventoryCategoryAssociate = new InventoryCategoryAssociate();
                    inventoryCategoryAssociate
                            .setId(UUID.randomUUID().toString())
                            .setInventoryCategoryId(e)
                            .setInventoryId(inventory.getId());
                    inventoryCategoryAssociateService.save(inventoryCategoryAssociate);
                }
            });*/
            result.succcess("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }


    @RequestMapping(path = "listToClient", method = RequestMethod.GET)
    public Result<?> listToClient(Inventory inventory,
                                  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Result<IPage<?>> result = new Result();
        Page<Inventory> page = new Page(pageNo, pageSize);
        QueryWrapper<?> queryWrapper = new QueryWrapper<>();
        try {
            IPage iPage = inventoryService.listToClient(page, queryWrapper, inventory);
            List<InventoryClientModel> list = (List<InventoryClientModel>) iPage.getRecords();
            Map<String, InventoryCategoryModel> map = inventoryCategoryService.getAllCategoryToRedis();
            list.forEach(e -> {
                if (e.getInventoryCategory() != null) {
                    String[] str = e.getInventoryCategory().split(",");
                    StringBuilder stringBuilder = new StringBuilder();
                    Arrays.stream(str).forEach(s -> {
                        if (map.containsKey(s)) {
                            stringBuilder.append(map.get(s).getCateName());
                            stringBuilder.append(",");
                        }
                    });
                    e.setInventoryCategoryName(stringBuilder.toString());
                }

                if (e.getInventoryCategoryFather() != null) {
                    String[] str = e.getInventoryCategoryFather().split(",");
                    StringBuilder stringBuilder = new StringBuilder();
                    Arrays.stream(str).forEach(s -> {
                        if (map.containsKey(s)) {
                            stringBuilder.append(map.get(s).getCateName());
                            stringBuilder.append(",");
                        }
                    });
                    e.setInventoryCategoryFatherName(stringBuilder.toString());
                }

                ImageNoteVO imageNoteVO = new ImageNoteVO();
                imageNoteVO
                        .setImageGroup(UploadFileConstant.INVENTORY_STATUE)
                        .setObjectId(e.getId());
                Result resultFegin = imageNoteClient.getFileListByObjectAndGroup(imageNoteVO);
                if (resultFegin.isSuccess()) {
                    List<LinkedHashMap> listImage = (List) resultFegin.getResult();
                    e.setImageListUrl(new LinkedList<>());
                    listImage.forEach(il -> {
                        Map<String, String> mapStr = il;
                        e.getImageListUrl().add(mapStr.get("url"));
                    });
                }

            });
            iPage.setRecords(list);
            result.setResult(iPage);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value = "根据id查商品", notes = "根据id查商品")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(name = "id") String id) {
        Result<Inventory> result = new Result<>();
        try {
            result.setResult(inventoryService.getById(id));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "根据id查商品(客户端)", notes = "根据id查商品(客户端)")
    @RequestMapping(path = "/selectByIdClient", method = RequestMethod.GET)
    public Result<?> selectByIdClient(@RequestParam(name = "id") String id, ServletRequest request) {
        Result<InventoryClientModel> result = new Result<>();
        try {
            InventoryClientModel inventoryClientModel = inventoryService.getByIdAll(id);
            //记录偏好
            if(this.getUserId()!= null && !StringUtils.isEmpty(inventoryClientModel.getInventoryCategoryFather())){
                shopPreferencesTypeService.increasesValue(
                        inventoryClientModel.getInventoryCategoryFather().split(","),
                        shopPreferencesChange.getLook(),
                        this.getUserId());
            }
            result.setResult(inventoryClientModel);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


    private String getUserId() {
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        if (authorityInformation == null)
            return null;
        return authorityInformation.getAuthorityId();
    }

}
