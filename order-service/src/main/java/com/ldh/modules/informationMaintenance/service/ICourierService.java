package com.ldh.modules.informationMaintenance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.model.CourierModel;

/**
 * @Description: 快递员
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
public interface ICourierService extends IService<Courier> {

    Page<CourierModel> list(Page page, QueryWrapper queryWrapper, Courier courier);

    void addHandle(Courier courier) throws Exception;

    void deleteAnyById(String id);

    Courier getByCode(String code);


}
