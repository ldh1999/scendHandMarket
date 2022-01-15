package com.ldh.modules.informationMaintenance.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.model.CourierModel;
import com.ldh.modules.informationMaintenance.model.PhysicalDistributionModel;

/**
 * @Description: 物流信息
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
public interface IPhysicalDistributionService extends IService<PhysicalDistribution> {

    Page<PhysicalDistributionModel> list(Page page, QueryWrapper queryWrapper, PhysicalDistribution physicalDistribution);

    PhysicalDistribution getNowPositionBuOrderPhyId(String orderPhysicalDistributionId);
}
