package com.ldh.modules.order.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.model.AfterSalesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 售后
 * @Author: jeecg-boot
 * @Date:   2022-02-26
 * @Version: V1.0
 */
@Mapper
public interface AfterSalesMapper extends BaseMapper<AfterSales> {

    Page<AfterSalesModel> list(Page page, QueryWrapper queryWrapper,@Param("afterSales") AfterSales afterSales);
}
