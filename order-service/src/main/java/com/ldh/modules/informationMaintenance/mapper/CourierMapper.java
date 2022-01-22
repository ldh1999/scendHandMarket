package com.ldh.modules.informationMaintenance.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.model.CourierModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 快递员
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Mapper
public interface CourierMapper extends BaseMapper<Courier> {

    Page<CourierModel> list(Page page, QueryWrapper queryWrapper, @Param("courier") Courier courier);

    Integer countByPhone(String phone);

    Courier getByCode(String code);

    CourierModel getAllByCode(String code);


}
