package com.ldh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.sys.entity.SysHorseLamp;
import com.ldh.modules.sys.mapper.SysHorseLampMapper;
import com.ldh.modules.sys.model.SysHorseLampModel;
import com.ldh.modules.sys.service.SysHorseLampService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysHorseLampServiceImpl extends ServiceImpl<SysHorseLampMapper, SysHorseLamp> implements SysHorseLampService {

    @Autowired
    private SysHorseLampMapper sysHorseLampMapper;

    @Override
    public Page<SysHorseLampModel> list(Page page, QueryWrapper queryWrapper, SysHorseLamp sysHorseLamp) {
        return sysHorseLampMapper.list(page, queryWrapper, sysHorseLamp);
    }
}
