package com.ldh.modules.merchant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.model.InventoryMerchantModel;
import com.ldh.modules.inventory.model.InventoryRecommendModel;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.service.MerchantService;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import common.Result;
import constant.UploadFileConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("merchant/client")
@Api("商家客户端")
public class MerchantClientController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @ApiOperation(value = "店铺首页商品展示", notes = "店铺首页商品展示")
    @RequestMapping(path = "/merchantInventory", method = RequestMethod.GET)
    public Result<?> listClientToRecommend(Inventory inventory,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "12") Integer pageSize
    ) {
        Result<IPage> result = new Result<>();
        Page<Inventory> page = new Page<>(pageNo, pageSize);
        try {
            IPage<InventoryMerchantModel> iPage = inventoryService.listToClientByMerchant(page,null, inventory);
            //获取
            List<InventoryMerchantModel> list = iPage.getRecords();
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
