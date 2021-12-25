package com.ldh.modules.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

    IPage<MerchantModel> list(Page page, QueryWrapper queryWrapper, @Param("merchant") Merchant merchant);

    MerchantModel selectById(String id);

    MerchantModel selectByUserId(String userId);

    List<MerchantModel> selectByIds(@Param("ids") String[] ids);
}
