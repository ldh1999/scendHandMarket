package com.ldh.modules.sys.service.impl;

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
    public int countByNo(String no) {
        return sysDictMapper.countByNo(no);
    }
}
