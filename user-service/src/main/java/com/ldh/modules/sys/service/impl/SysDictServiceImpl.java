package com.ldh.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.sys.entity.SysDict;
import com.ldh.modules.sys.entity.SysDictItem;
import com.ldh.modules.sys.mapper.SysDictItemMapper;
import com.ldh.modules.sys.mapper.SysDictMapper;
import com.ldh.modules.sys.model.DictModel;
import com.ldh.modules.sys.service.SysDictItemService;
import com.ldh.modules.sys.service.SysDictService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService  {

    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Autowired
    private SysDictItemService sysDictItemService;

    @Override
    public int countByNo(SysDict dict) {
        return sysDictMapper.countByNo(dict);
    }

    @Override
    public IPage<SysDict> list(Page<SysDict> page, SysDict sysDict, QueryWrapper queryWrapper) {
        return sysDictMapper.list(page, sysDict, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void deleteByIdAnywhere(String id) {
        this.removeById(id);
        sysDictItemMapper.deleteByDictId(id);
    }

    @Override
    public Map<String, List<DictModel>> queryAllDictItems() {
        Map<String, List<DictModel>> map = new HashMap<>();
        List<SysDict> sysDictList = sysDictMapper.getCodeAll();
        List<DictModel> dictModelList = sysDictItemService.getAllDictItem();
        sysDictList.stream().forEach(e->{
            List<DictModel> list = dictModelList.stream().filter(s-> s.getDictId().equals(e.getId())).collect(Collectors.toList());
            map.put(e.getDictNo(),list);
        });
        return map;
    }
}
