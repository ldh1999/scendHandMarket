package com.ldh.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.sys.entity.SysHorseLamp;
import com.ldh.modules.sys.model.SysHorseLampModel;

public interface SysHorseLampService extends IService<SysHorseLamp> {

     Page<SysHorseLampModel> list(Page page, QueryWrapper queryWrapper, SysHorseLamp sysHorseLamp);

}
