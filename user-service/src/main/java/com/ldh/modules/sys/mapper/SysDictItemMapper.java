package com.ldh.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
    int countByItemKey(@Param(value = "item") SysDictItem item);

    IPage<SysDictItem> list(Page page,
                            @Param(value = "item") SysDictItem sysDictItem,
                            QueryWrapper queryWrapper,
                            @Param(value = "dictId") String dictId);
}
