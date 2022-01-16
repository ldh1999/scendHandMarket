package com.ldh.modules.informationMaintenance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.mapper.PhysicalDistributionMapper;
import com.ldh.modules.informationMaintenance.model.PhysicalDistributionModel;
import com.ldh.modules.informationMaintenance.service.IPhysicalDistributionService;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 物流信息
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Service
public class PhysicalDistributionServiceImpl extends ServiceImpl<PhysicalDistributionMapper, PhysicalDistribution> implements IPhysicalDistributionService {

    @Autowired
    private PhysicalDistributionMapper physicalDistributionMapper;

    @Autowired
    private OrderPhysicalDistributionService orderPhysicalDistributionService;

    @Override
    public Page<PhysicalDistributionModel> list(Page page, QueryWrapper queryWrapper, PhysicalDistribution physicalDistribution) {
        return physicalDistributionMapper.list(page, queryWrapper, physicalDistribution);
    }

    @Override
    public PhysicalDistribution getNowPositionBuOrderPhyId(String orderPhysicalDistributionId) {
        PhysicalDistribution physicalDistribution = new PhysicalDistribution();
        QueryWrapper<PhysicalDistribution> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_physical_distribution_id", orderPhysicalDistributionId);
        queryWrapper.orderByDesc("create_time");
        List<PhysicalDistribution> list = this.list(queryWrapper);
        if (list.isEmpty()){
            OrderPhysicalDistribution orderPhysicalDistribution = orderPhysicalDistributionService.getById(orderPhysicalDistributionId);
            physicalDistribution.setNowPositionName(orderPhysicalDistribution.getStartPositionDetail());
        }else {
            physicalDistribution = list.get(0);
        }
        return physicalDistribution;
    }

    @Override
    public List<PhysicalDistribution> getByOrderPhysicalId(String id) {
        return physicalDistributionMapper.getByOrderPhysicalId(id);
    }
}
