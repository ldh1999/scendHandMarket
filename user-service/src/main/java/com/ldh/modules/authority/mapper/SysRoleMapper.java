package com.ldh.modules.authority.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.SysRole;
import common.OptionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Max;
import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    IPage<SysRole> list(Page page, QueryWrapper queryWrapper, @Param("sysRole") SysRole sysRole);

    List<OptionModel> getAllOption();

    Integer countByNo(@Param("sysRole") SysRole sysRole);

    Integer countRoleByUserIdAndRoleNo(String userId, String roleNo);

    Integer countRoleByUserIdAndRoleList(String userId, List<String> roleNoList);
}
