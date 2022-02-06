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
        List<ShopPreferencesType> list = new LinkedList<>();
        for (String typeId : typeIds) {
            if (shopPreferencesTypeMapper.countByUserAndType(userId, typeId) <= 0){
                ShopPreferencesType shopPreferencesType = new ShopPreferencesType();
                shopPreferencesType
                        .setUserId(userId)
                        .setInventoryTypeId(typeId)
                        .setPreferencesValue(0f);
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
