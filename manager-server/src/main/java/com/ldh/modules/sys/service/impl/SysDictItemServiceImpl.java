package com.ldh.modules.sys.service.impl;

import com.ldh.modules.sys.service.SysDictItemService;
import com.ldh.sysService.client.SysDictClient;
import com.ldh.sysService.pojo.SysDict;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SysDictItemServiceImpl implements SysDictItemService {

    @Autowired
    private SysDictClient sysDictClient;

    @Override
    public Result<?> list(SysDict sysDict, Integer pageNo, Integer pageSize, String column, String order) {
        return sysDictClient.list(sysDict, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> add(SysDict sysDict) {
        return sysDictClient.add(sysDict);

    }

    @Override
    public Result<?> deleteById(String id) {
        return sysDictClient.deleteById(id);

    }

    @Override
    public Result<?> updateById(SysDict sysDict) {
        return sysDictClient.updateById(sysDict);

    }

    @Override
    public Result<?> selectById(String id) {
        return sysDictClient.selectById(id);

    }

    @Override
    public Result<?> queryAllDictItems() {
        return sysDictClient.queryAllDictItems();

    }
}
