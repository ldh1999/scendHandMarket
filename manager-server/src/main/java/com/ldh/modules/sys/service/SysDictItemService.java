package com.ldh.modules.sys.service;

import com.ldh.sysService.pojo.SysDict;
import common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface SysDictItemService {

    Result<?> list(SysDict sysDict, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> add(SysDict sysDict);

    Result<?> deleteById(String id);

    Result<?> updateById(SysDict sysDict);

    Result<?> selectById(String id);

    Result<?> queryAllDictItems();

}
