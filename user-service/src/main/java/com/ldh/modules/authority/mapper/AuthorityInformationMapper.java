package com.ldh.modules.authority.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityInformationMapper extends BaseMapper<AuthorityInformation> {
    IPage<AuthorityInformation> list(AuthorityInformation authorityInformation, Page page, QueryWrapper queryWrapper);
    int countUserName(String userName);
}
