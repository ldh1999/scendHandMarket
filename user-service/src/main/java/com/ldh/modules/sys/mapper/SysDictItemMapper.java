package com.ldh.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.model.DictModel;
import common.OptionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
    int countByItemKey(@Param(value = "item") SysDictItem item);

    IPage<SysDictItem> list(Page page,
                            @Param(value = "item") SysDictItem sysDictItem,
                            QueryWrapper queryWrapper,
                            @Param(value = "dictId") String dictId);

    void deleteByDictId(String dictId);

    String getItemValueBydictNoAndItemKey(String dictNo, String itemKey);

    List<OptionModel> getOptionByDictNo(String dictNo);

    List<DictModel> getAllDictItem();
}
