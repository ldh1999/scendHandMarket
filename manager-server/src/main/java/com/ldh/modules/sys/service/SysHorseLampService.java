package com.ldh.modules.sys.service;

import com.ldh.sysService.model.SysHorseLampVO;
import com.ldh.sysService.pojo.SysHorseLamp;
import common.Result;

public interface SysHorseLampService {
    Result<?> list(SysHorseLamp sysHorseLamp,
                    Integer pageNo, Integer pageSize, String column, String order);

    Result<?> homeListClient();

    Result<?> add(SysHorseLampVO sysHorseLampVO);

    Result<?> deleteById(String id);

    Result<?> updateById(SysHorseLamp sysHorseLamp);
}
