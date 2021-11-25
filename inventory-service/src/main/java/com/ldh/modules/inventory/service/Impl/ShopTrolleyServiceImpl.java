package com.ldh.modules.inventory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.inventory.entity.ShopTrolley;
import com.ldh.modules.inventory.mapper.ShopTrolleyMapper;
import com.ldh.modules.inventory.model.ShopTrolleyClientModel;
import com.ldh.modules.inventory.service.ShopTrolleyService;
import com.ldh.otherResourceService.client.ImageNoteGetClient;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import common.Result;
import constant.UploadFileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShopTrolleyServiceImpl extends ServiceImpl<ShopTrolleyMapper, ShopTrolley> implements ShopTrolleyService {

    @Autowired
    private ShopTrolleyMapper shopTrolleyMapper;

    @Autowired
    private ImageNoteGetClient imageNoteGetClient;

    @Override
    public Page<ShopTrolleyClientModel> listToClient(Page page, QueryWrapper queryWrapper, ShopTrolley shopTrolley) throws Exception {

        Page<ShopTrolleyClientModel> iPage = shopTrolleyMapper.listToClient(page, shopTrolley, queryWrapper);
        List<ShopTrolleyClientModel> pageList = iPage.getRecords();
        if (pageList.size()<1){
            return iPage;
        }
        ImageListGetVO imageGetVO = new ImageListGetVO();
        List<String> objectListId = new LinkedList<>();
        Set<String> set = new HashSet();
        pageList.stream().forEach(e->{
            if (!set.contains(e.getInventoryId())){
                set.add(e.getInventoryId());
            }
        });
        set.stream().forEach(e->{
            objectListId.add(e);
        });
        imageGetVO
                .setObjectId(objectListId)
                .setImgGroup(UploadFileConstant.INVENTORY_STATUE);
        Result<List<ImageNoteModel>> result = imageNoteGetClient.getByObjectIdAndImgGroupList(imageGetVO);
        if (result.isSuccess()){
            List<ImageNoteModel> imageNoteModelList = result.getResult();
            Map<String, List<String>> map = new HashMap<>();
            imageNoteModelList.stream().forEach(e->{
                if (!map.containsKey(e.getObjectId())){
                    List<String> list = new LinkedList<>();
                    list.add(e.getImgPath());
                    map.put(e.getObjectId(), list);
                }else {
                    map.get(e.getObjectId()).add(e.getImgPath());
                }
            });
            pageList.stream().forEach(e->{
                if (map.containsKey(e.getInventoryId())){
                    e.setImgPathList(map.get(e.getInventoryId()));
                }
            });
            iPage.setRecords(pageList);

        }else {
            throw new Exception("open fegin error");
        }

        return iPage;
    }

    @Override
    public Integer countByUserIdAndInventoryId(String userId, String inventoryId) {
        return shopTrolleyMapper.countByUserIdAndInventoryId(userId, inventoryId);
    }

    @Override
    public Integer updateBySyn(ShopTrolley shopTrolley) {
        return shopTrolleyMapper.updateBySyn(shopTrolley);
    }

    @Override
    public ShopTrolleyClientModel selectByUserIdAndInventoryId(ShopTrolley shopTrolley) {
        return shopTrolleyMapper.selectByUserIdAndInventoryId(shopTrolley);
    }
}
