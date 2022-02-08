package com.ldh.modules.inventory.service.Impl;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.Inventory;
import com.ldh.modules.inventory.mapper.InventoryCategoryAssociateMapper;
import com.ldh.modules.inventory.mapper.InventoryMapper;
import com.ldh.modules.inventory.model.*;
import com.ldh.modules.inventory.service.InventoryService;
import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import constant.UploadFileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private InventoryCategoryAssociateMapper inventoryCategoryAssociateMapper;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Autowired
    private HttpServletRequest request;

    @Override
    public IPage<InventoryModel> list(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        return inventoryMapper.list(page, queryWrapper, inventory);
    }

    @Override
    public void deleteAnyById(String id) {
        this.removeById(id);
        inventoryCategoryAssociateMapper.deleteByInventoryId(id);
    }

    @Override
    public IPage<InventoryClientModel> listToClient(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        IPage<InventoryClientModel> page1 = inventoryMapper.listToClient(page, queryWrapper, inventory);
        List<InventoryClientModel> list = page1.getRecords();
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
        if (imageNoteModels == null){
            return page1;
        }
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
                e.setFirstImagePath(modelList.get(min).getImgPath());
            }
        });
        page1.setRecords(list);
        return page1;
    }

    @Override
    public IPage<InventoryRecommendModel> getRecommendList(Page page) {
        AuthorityInformation user = this.getUser();
        IPage<InventoryRecommendModel> iPage = null;
        //判断是否登录  ，登录的给推荐 ，没登录的给随机
        if (user != null){
            iPage = inventoryMapper.getRecommendList(page, user.getAuthorityId());
            //如果该用户第一次使用本系统没有偏向，也给随机
            if (iPage.getRecords().size() == 0){
                iPage = inventoryMapper.getRandInventory(page);
            }
        }else {
            iPage = inventoryMapper.getRandInventory(page);
        }
        return iPage;
    }

    @Override
    public IPage<InventoryRecommendModel> getRandList(Page page) {
        return inventoryMapper.getRandInventory(page);
    }

    @Override
    public InventoryClientModel getByIdAll(String id) throws Exception {
        InventoryClientModel inventoryClientModel = inventoryMapper.selectByIdAll(id);
        ImageGetVO imageGetVO = new ImageGetVO();
        imageGetVO.setImgGroup(UploadFileConstant.INVENTORY_STATUE)
                .setObjectId(id);
        Result<List<ImageNoteModel>> result = imageNoteGetClient.getByObjectIdAndImgGroup(imageGetVO);
        if (result.isSuccess()){
            List<ImageNoteModel> list = result.getResult();
            List<String> imgPaths = new LinkedList<>();
            if (list != null){
                list.stream().forEach(e->{
                    imgPaths.add(e.getImgPath());
                });
                inventoryClientModel.setImageListUrl(imgPaths);
            }
        }else {
            throw new Exception("inventory fegin error");
        }
        return inventoryClientModel;
    }

    @Override
    public List<Inventory> selectByIds(String[] ids) {
        return inventoryMapper.selectByIds(ids);
    }

    @Override
    public IPage<InventoryMerchantModel> listToClientByMerchant(Page page, QueryWrapper queryWrapper, Inventory inventory) {
        return inventoryMapper.listToClientByMerchant(page, queryWrapper, inventory);
    }

    @Override
    public IPage<InventoryCategoryClientModel> listToClientByCategory(Page page, QueryWrapper queryWrapper, String categoryId) {
        return inventoryMapper.listToClientByCategory(page, queryWrapper, categoryId);
    }

    @Override
    public List<AutoSearchResponse> autoSearch(String str) {
        return inventoryMapper.getSearchLimit(str,10);
    }

    private AuthorityInformation getUser(){
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        return authorityInformation;
    }
}
