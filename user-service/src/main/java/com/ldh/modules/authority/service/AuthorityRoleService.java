package com.ldh.modules.authority.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.model.AuthorityRoleInformationModel;
import org.apache.ibatis.annotations.Param;

public interface AuthorityRoleService extends IService<AuthorityRoleInformation> {
    IPage<AuthorityRoleInformationModel> list(Page page, QueryWrapper queryWrapper, @Param("authorityRoleInformation") AuthorityRoleInformation authorityRoleInformation);

    Integer countAuthorityRoleByAuthorityId(String authorityId,String sysRoleId);

    Integer deleteByAuthorityId(String authorityId);
}
