package com.ldh.modules.shop.service.impl;

import com.ldh.modules.shop.entity.ShopPreferencesType;
import com.ldh.modules.shop.mapper.ShopPreferencesTypeMapper;
import com.ldh.modules.shop.model.ShopPreferencesTypeModel;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 购物偏好
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */
@Service
public class ShopPreferencesTypeServiceImpl extends ServiceImpl<ShopPreferencesTypeMapper, ShopPreferencesType> implements ShopPreferencesTypeService {

    @Autowired
    private ShopPreferencesTypeMapper shopPreferencesTypeMapper;

    @Override
    @Transactional
    public void increasesValue(String[] typeIds, Float num, String userId) {
        //取相对较小的偏向值，如果没有则为0
        Float min = 0f;
        List<ShopPreferencesTypeModel> listMin = this.getPreferenceList(userId);
        if (!listMin.isEmpty()){
            min = listMin.get(listMin.size()-1).getPreferencesValue();
        }
        //用户第一次访问该偏向，偏向值取相对较小值
        List<ShopPreferencesType> list = new LinkedList<>();
        for (String typeId : typeIds) {
            if (shopPreferencesTypeMapper.countByUserAndType(userId, typeId) <= 0){
                ShopPreferencesType shopPreferencesType = new ShopPreferencesType();
                shopPreferencesType
                        .setUserId(userId)
                        .setInventoryTypeId(typeId)
                        .setPreferencesValue(min);
                list.add(shopPreferencesType);
            }
        }
        if (!list.isEmpty()){
            this.saveBatch(list);
        }
        shopPreferencesTypeMapper.updateValueAdd(userId, typeIds, num);
    }

    @Override
    public List<ShopPreferencesTypeModel> getPreferenceList(String userId) {
        return shopPreferencesTypeMapper.getPreferenceList(userId);
    }

    @Override
    public void setMedianToTypeId(String[] typeIds, String userId) {
        List<ShopPreferencesTypeModel> list = this.getPreferenceList(userId);
        if (list.isEmpty()){
            return;
        }
        Float min = list.get(list.size()-1).getPreferencesValue();
        shopPreferencesTypeMapper.updateValue(userId, typeIds, min);
    }

/*    private AuthorityInformation getUser(){
        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
        return authorityInformation;
    }*/
}
