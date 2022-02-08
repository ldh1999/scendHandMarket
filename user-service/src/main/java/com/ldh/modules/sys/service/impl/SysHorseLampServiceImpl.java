package com.ldh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.inventoryService.client.InventoryClient;
import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.inventoryService.model.MerchantModel;
import com.ldh.inventoryService.pojo.Inventory;
import com.ldh.inventoryService.pojo.Merchant;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.entity.SysHorseLamp;
import com.ldh.modules.sys.mapper.SysHorseLampMapper;
import com.ldh.modules.sys.model.SysHorseLampClientShowModel;
import com.ldh.modules.sys.model.SysHorseLampModel;
import com.ldh.modules.sys.service.SysDictItemService;
import com.ldh.modules.sys.service.SysHorseLampService;
import com.ldh.otherResourceService.client.ImageNoteClient;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import common.OptionModel;
import common.Result;
import constant.UploadFileConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysHorseLampServiceImpl extends ServiceImpl<SysHorseLampMapper, SysHorseLamp> implements SysHorseLampService {

    @Autowired
    private SysHorseLampMapper sysHorseLampMapper;

    @Autowired
    private SysDictItemService sysDictItemService;


    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private ImageNoteClient imageNoteClient;

    @Autowired
    private MerchantClient merchantClient;

    @Override
    public Page<SysHorseLampModel> list(Page page, QueryWrapper queryWrapper, SysHorseLamp sysHorseLamp) {

        Page<SysHorseLampModel> iPage = sysHorseLampMapper.list(page, queryWrapper, sysHorseLamp);
        List<OptionModel> dictOptionList = sysDictItemService.getOptionByDictNo("object_type");
        Map<String, OptionModel> optionMap = dictOptionList.stream().collect(Collectors.toMap(OptionModel::getKey, r->r));
        List<SysHorseLampModel> resultList = iPage.getRecords();
        resultList.stream().forEach(e->{
            if (!optionMap.containsKey(e.getHorseType())){
                throw new RuntimeException("dict_item is not field");
            }
            //指向商家
            if (e.getHorseType().equals("marchant_choose")){
                Result<MerchantModel> result = merchantClient.selectById(e.getObjectId());
                if (result.isSuccess() && result.getResult()!=null){
                    MerchantModel merchantModel = result.getResult();
                    e.setObjectName(merchantModel.getMerchantName());
                }else {
                    e.setObjectName("对象已被删除或损坏");
                }
            }
            //指向商品
            if (e.getHorseType().equals("inventory_choose")){
                Result<Inventory> result = inventoryClient.getById(e.getObjectId());
                if (result.isSuccess()&& result.getResult() != null){
                    Inventory inventory = result.getResult();
                    e.setObjectName(inventory.getInventoryName());
                }else {
                    e.setObjectName("对象已被删除或损坏");
                }
            }
            ImageGetVO imageGetVO = new ImageGetVO();
            imageGetVO.setObjectId(e.getId())
                    .setImgGroup(UploadFileConstant.HORSE_LAMP_MANAGER);
            Result<List<ImageNoteModel>> result = imageNoteClient.getByObjectIdAndImgGroup(imageGetVO);
            if (result.isSuccess()) {
                List<ImageNoteModel> imageNoteModelList = result.getResult();
                if (imageNoteModelList.size() != 0){
                    e.setImagePath(imageNoteModelList.get(0).getImgPath());
                }
            }
        });
        iPage.setRecords(resultList);
        return iPage;
    }

    @Override
    public List<SysHorseLampClientShowModel> getHomeList() {
        List<SysHorseLampClientShowModel> list = sysHorseLampMapper.homeClientList();
        list.stream().forEach(e->{
            ImageGetVO imageGetVO = new ImageGetVO();
            imageGetVO.setObjectId(e.getId())
                    .setImgGroup(UploadFileConstant.HORSE_LAMP_MANAGER);
            Result<List<ImageNoteModel>> result = imageNoteClient.getByObjectIdAndImgGroup(imageGetVO);
            if (result.isSuccess()) {
                List<ImageNoteModel> imageNoteModelList = result.getResult();
                if (imageNoteModelList.size() != 0){
                    e.setImagePath(imageNoteModelList.get(0).getImgPath());
                }
            }
        });
        return list;
    }
}
