package com.ldh.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.sys.entity.SysDict;

public interface SysDictService extends IService<SysDict> {
    int countByNo(String no);
}
