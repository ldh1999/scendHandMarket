package com.ldh.modules.informationMaintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import com.ldh.modules.informationMaintenance.mapper.CourierMapper;
import com.ldh.modules.informationMaintenance.model.CourierModel;
import com.ldh.modules.informationMaintenance.service.CourierServicesCompanyService;
import com.ldh.modules.informationMaintenance.service.ICourierService;
import com.ldh.rabbitmq.service.UserRabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 快递员
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Service
public class CourierServiceImpl extends ServiceImpl<CourierMapper, Courier> implements ICourierService {

    @Autowired
    private CourierMapper courierMapper;

    @Autowired
    private UserRabbitMQService userRabbitMQService;

    @Autowired
    private CourierServicesCompanyService courierServicesCompanyService;

    @Override
    public Page<CourierModel> list(Page page, QueryWrapper queryWrapper, Courier courier) {
        return courierMapper.list(page, queryWrapper, courier);
    }

    @Override
    @Transactional
    public void addHandle(Courier courier) throws Exception {
        if (courierMapper.countByPhone(courier.getCourierPhone()) > 0){
            throw new Exception("员工手机号重复");
        }
        CourierServicesCompany  courierServicesCompany = courierServicesCompanyService.getById(courier.getCourierServicesCompanyId());
        courier.setCourierCode(courierServicesCompany.getCourierServicesCompanyCode()+"_"+courier.getCourierPhone());
        userRabbitMQService.register(courier);
        this.save(courier);
    }

    @Override
    public void deleteAnyById(String id) {
        Courier courier = this.getById(id);
        userRabbitMQService.deleteByUsername(courier.getCourierCode());
        this.removeById(id);
    }

    @Override
    public Courier getByCode(String code) {
        return courierMapper.getByCode(code);
    }
}
