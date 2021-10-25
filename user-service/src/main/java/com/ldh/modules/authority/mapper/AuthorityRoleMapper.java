package com.ldh.modules.authority.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.model.AuthorityRoleInformationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Mapper
public interface AuthorityRoleMapper extends BaseMapper<AuthorityRoleInformation> {
    IPage<AuthorityRoleInformationModel> list(Page page, QueryWrapper queryWrapper, @Param("authorityRoleInformation") AuthorityRoleInformation authorityRoleInformation);

    Integer countAuthorityRoleByAuthorityId(String authorityId,String sysRoleId);

    @Transactional(rollbackFor = {SQLException.class})
    Integer deleteBySysRole(String sysRoleId);

    Integer deleteByAuthorityId(String authorityId);

}
