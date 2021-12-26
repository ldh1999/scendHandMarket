package com.ldh.modules.sys.service;

import com.ldh.sysService.pojo.SysDictItem;
import common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface SysDictService {

    Result<?> list(SysDictItem sysDictItem, String dictId, Integer pageNo, Integer pageSize, String column, String order);

    Result<?> insert(SysDictItem sysDictItem);

    Result<?> deleteById(String id);

    Result<?> getItemValueBydictNoAndItemKey(String dictNo, String itemKey);

    Result<?> getOptionByDictNo(String dictNo);

}
