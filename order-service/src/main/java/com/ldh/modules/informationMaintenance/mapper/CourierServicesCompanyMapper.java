package com.ldh.modules.informationMaintenance.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.xml.namespace.QName;

/**
 * @Description: 快递公司维护
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */

@Mapper
public interface CourierServicesCompanyMapper extends BaseMapper<CourierServicesCompany> {

    Page<CourierServicesCompany> list(Page page, QueryWrapper queryWrapper, @Param("courierServicesCompany") CourierServicesCompany courierServicesCompany);
}
