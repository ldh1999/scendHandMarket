package com.ldh.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.sys.entity.SysDictItem;
import org.apache.ibatis.annotations.Param;

public interface SysDictItemService extends IService<SysDictItem> {
    int countByItemKey(@Param(value = "item") SysDictItem item);

    IPage<SysDictItem> list(Page page,
                            @Param(value = "item") SysDictItem sysDictItem,
                            QueryWrapper queryWrapper,
                            @Param(value = "dictId") String dictId);
}
