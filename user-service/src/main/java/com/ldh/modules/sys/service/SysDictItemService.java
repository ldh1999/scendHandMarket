package com.ldh.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.sys.entity.SysDictItem;
import common.OptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictItemService extends IService<SysDictItem> {
    int countByItemKey(@Param(value = "item") SysDictItem item);

    IPage<SysDictItem> list(Page page,
                            @Param(value = "item") SysDictItem sysDictItem,
                            QueryWrapper queryWrapper,
                            @Param(value = "dictId") String dictId);

    String getItemValueBydictNoAndItemKey(String dictNo, String itemKey);

    List<OptionModel> getOptionByDictNo(String dictNo);

}
