package com.ldh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.sys.entity.SysDict;
import com.ldh.modules.sys.mapper.SysDictMapper;
import com.ldh.modules.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService  {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public int countByNo(SysDict dict) {
        return sysDictMapper.countByNo(dict);
    }

    @Override
    public IPage<SysDict> list(Page<SysDict> page, SysDict sysDict, QueryWrapper queryWrapper) {
        return sysDictMapper.list(page, sysDict, queryWrapper);
    }
}
