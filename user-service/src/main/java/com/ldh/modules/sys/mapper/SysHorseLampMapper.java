package com.ldh.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.sys.entity.SysHorseLamp;
import com.ldh.modules.sys.model.SysHorseLampClientShowModel;
import com.ldh.modules.sys.model.SysHorseLampModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysHorseLampMapper extends BaseMapper<SysHorseLamp> {

    Page<SysHorseLampModel> list(Page page, QueryWrapper queryWrapper, @Param("sysHorseLamp") SysHorseLamp sysHorseLamp);

    List<SysHorseLampClientShowModel> homeClientList();
}
