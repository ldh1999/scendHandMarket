package com.ldh.modules.sys.service.impl;

import com.ldh.modules.sys.service.SysDictService;
import com.ldh.sysService.client.SysDictItemClient;
import com.ldh.sysService.pojo.SysDictItem;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictItemClient sysDictItemClient;

    @Override
    public Result<?> list(SysDictItem sysDictItem, String dictId, Integer pageNo, Integer pageSize, String column, String order) {
        return sysDictItemClient.list(sysDictItem, dictId, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> insert(SysDictItem sysDictItem) {
        return sysDictItemClient.insert(sysDictItem);

    }

    @Override
    public Result<?> deleteById(String id) {
        return sysDictItemClient.deleteById(id);

    }

    @Override
    public Result<?> getItemValueBydictNoAndItemKey(String dictNo, String itemKey) {
        return sysDictItemClient.getItemValueBydictNoAndItemKey(dictNo,itemKey);

    }

    @Override
    public Result<?> getOptionByDictNo(String dictNo) {
        return sysDictItemClient.getOptionByDictNo(dictNo);

    }
}
