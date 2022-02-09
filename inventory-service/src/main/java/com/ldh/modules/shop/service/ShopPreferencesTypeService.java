package com.ldh.modules.shop.service;

import com.ldh.modules.shop.entity.ShopPreferencesType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.shop.model.ShopPreferencesTypeModel;

import java.util.List;

/**
 * @Description: 购物偏好
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */
public interface ShopPreferencesTypeService extends IService<ShopPreferencesType> {

    void increasesValue(String[] typeIds, Float num,String userId);

    List<ShopPreferencesTypeModel> getPreferenceList(String userId);
    /**
     * 将该偏好值取最低
     */
    void setMedianToTypeId(String[] typeIds, String userId);

    /**
     * 如果商品类型删除的话，该类型的偏好栏则消失
     * @param typeId
     * @return
     */
    Integer deleteByTypeId(String typeId);


}
