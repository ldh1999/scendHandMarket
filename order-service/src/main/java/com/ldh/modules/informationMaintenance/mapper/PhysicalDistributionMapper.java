package com.ldh.modules.informationMaintenance.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.model.PhysicalDistributionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 物流信息
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Mapper
public interface PhysicalDistributionMapper extends BaseMapper<PhysicalDistribution> {

    Page<PhysicalDistributionModel> list(Page page, QueryWrapper queryWrapper, @Param("physicalDistribution") PhysicalDistribution physicalDistribution);

    List<PhysicalDistribution> getByOrderPhysicalId(String id);
}
