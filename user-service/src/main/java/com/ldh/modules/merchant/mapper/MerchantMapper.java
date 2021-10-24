package com.ldh.modules.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.merchant.entity.Merchant;
import com.ldh.modules.merchant.model.MerchantVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

    IPage<MerchantVO> list(Page page, QueryWrapper queryWrapper, @Param("merchant") Merchant merchant);

    MerchantVO selectById(String id);
}
