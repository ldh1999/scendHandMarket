package com.ldh.modules.authority.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthorityInformationMapper extends BaseMapper<AuthorityInformation> {
    IPage<AuthorityInformationModel> list(Page page, @Param(value = "authorityInformation") AuthorityInformation authorityInformation, QueryWrapper queryWrapper);

    int countUserName(AuthorityInformation authorityInformation);

    AuthorityInformationModel findByUserName(String username);
}
