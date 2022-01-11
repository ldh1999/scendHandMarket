package com.ldh.modules.informationMaintenance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import common.OptionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 快递公司维护
 * @Author: ldh
 * @Date:   2022-01-08
 * @Version: V1.0
 */
public interface CourierServicesCompanyService extends IService<CourierServicesCompany> {

    Page<CourierServicesCompany> list(Page page, QueryWrapper queryWrapper, CourierServicesCompany courierServicesCompany);

    List<OptionModel> getAllOption();

}
