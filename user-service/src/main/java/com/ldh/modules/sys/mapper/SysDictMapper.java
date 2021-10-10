package com.ldh.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {
    int countByNo(@Param(value = "dict") SysDict dict);

    IPage<SysDict> list(Page<SysDict> page, SysDict sysDict, QueryWrapper queryWrapper);
}
