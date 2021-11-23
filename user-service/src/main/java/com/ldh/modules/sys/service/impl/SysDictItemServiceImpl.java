package com.ldh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.mapper.SysDictItemMapper;
import com.ldh.modules.sys.model.DictModel;
import com.ldh.modules.sys.service.SysDictItemService;
import common.OptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Override
    public int countByItemKey(SysDictItem item) {
        return sysDictItemMapper.countByItemKey(item);
    }

    @Override
    public IPage<SysDictItem> list(Page page, SysDictItem sysDictItem, QueryWrapper queryWrapper, String dictId) {
        return sysDictItemMapper.list(page, sysDictItem, queryWrapper, dictId);
    }

    @Override
    public String getItemValueBydictNoAndItemKey(String dictNo, String itemKey) {
        return sysDictItemMapper.getItemValueBydictNoAndItemKey(dictNo, itemKey);
    }

    @Override
    public List<OptionModel> getOptionByDictNo(String dictNo) {
        return sysDictItemMapper.getOptionByDictNo(dictNo);
    }

    @Override
    public List<DictModel> getAllDictItem() {
        return sysDictItemMapper.getAllDictItem();
    }
}
