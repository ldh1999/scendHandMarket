package com.ldh.modules.informationMaintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import com.ldh.modules.informationMaintenance.mapper.CourierServicesCompanyMapper;
import com.ldh.modules.informationMaintenance.service.CourierServicesCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 快递公司维护
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Service
public class CourierServicesCompanyServiceImpl extends ServiceImpl<CourierServicesCompanyMapper, CourierServicesCompany> implements CourierServicesCompanyService {

    @Autowired
    private CourierServicesCompanyMapper courierServicesCompanyMapper;

    @Override
    public Page<CourierServicesCompany> list(Page page, QueryWrapper queryWrapper, CourierServicesCompany courierServicesCompany) {
        return courierServicesCompanyMapper.list(page, queryWrapper, courierServicesCompany);
    }
}
