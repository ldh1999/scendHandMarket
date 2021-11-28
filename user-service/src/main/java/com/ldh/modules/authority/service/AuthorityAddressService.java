package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.AuthorityAddress;
import com.ldh.modules.authority.model.AuthorityAddressModel;
import common.OptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户地址
 * @Author: jeecg-boot
 * @Date:   2021-11-27
 * @Version: V1.0
 */
public interface AuthorityAddressService extends IService<AuthorityAddress> {

    Page<AuthorityAddressModel> getListClient(Page page, QueryWrapper queryWrapper, @Param("authorityAddress") AuthorityAddress authorityAddress);

    List<OptionModel> getOptionByUserId(String id);

}
