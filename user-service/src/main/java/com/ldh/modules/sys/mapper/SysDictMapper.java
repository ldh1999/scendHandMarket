package com.ldh.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.sys.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {
    int countByNo(String no);
}
