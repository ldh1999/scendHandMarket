package com.ldh.modules.sys.service.impl;

import com.ldh.modules.sys.service.SysHorseLampService;
import com.ldh.sysService.client.SysHorseLampClient;
import com.ldh.sysService.model.SysHorseLampVO;
import com.ldh.sysService.pojo.SysHorseLamp;
import common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysHorseLampServiceImpl implements SysHorseLampService {

    @Autowired
    private SysHorseLampClient sysHorseLampClient;

    @Override
    public Result<?> list(SysHorseLamp sysHorseLamp, Integer pageNo, Integer pageSize, String column, String order) {
        return sysHorseLampClient.list(sysHorseLamp, pageNo, pageSize, column, order);

    }

    @Override
    public Result<?> homeListClient() {
        return sysHorseLampClient.homeListClient();

    }

    @Override
    public Result<?> add(SysHorseLampVO sysHorseLampVO) {
        return sysHorseLampClient.add(sysHorseLampVO);

    }

    @Override
    public Result<?> deleteById(String id) {
        return sysHorseLampClient.deleteById(id);
    }

    @Override
    public Result<?> updateById(SysHorseLamp sysHorseLamp) {
        return sysHorseLampClient.updateById(sysHorseLamp);

    }
}
