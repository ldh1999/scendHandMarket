package com.ldh.modules.shop.mapper;

import com.ldh.modules.shop.entity.ShopPreferencesType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.shop.model.ShopPreferencesTypeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 购物偏好
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */

@Mapper
public interface ShopPreferencesTypeMapper extends BaseMapper<ShopPreferencesType> {

    Integer countByUserAndType(String userId, String typeId);

    void updateValueAdd(String userId, String[] typeIds,Float num);

    List<ShopPreferencesTypeModel> getPreferenceList(String userId);

    void updateValue(String userId, String[] typeIds,Float num);


}
