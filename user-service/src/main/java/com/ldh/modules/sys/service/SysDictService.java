package com.ldh.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.sys.entity.SysDict;
import com.ldh.modules.sys.model.DictModel;

import java.util.List;
import java.util.Map;

public interface SysDictService extends IService<SysDict> {
    int countByNo(SysDict dict);

    IPage<SysDict> list(Page<SysDict> page, SysDict sysDict, QueryWrapper queryWrapper);

    void deleteByIdAnywhere(String id);

    Map<String, List<DictModel>> queryAllDictItems();
}
