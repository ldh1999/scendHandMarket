package com.ldh.modules.authority.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.authority.model.AuthorityAddressModel;
import common.OptionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户地址
 * @Author: jeecg-boot
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Mapper
public interface AuthorityAddressMapper extends BaseMapper<AuthorityAddress> {

    Page<AuthorityAddressModel> getListClient(Page page, QueryWrapper queryWrapper, @Param("authorityAddress") AuthorityAddress authorityAddress);

    List<OptionModel> getOptionByUserId(String id);
}
