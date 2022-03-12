package com.ldh.modules.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.AfterSales;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.order.model.AfterSalesDetailsModel;
import com.ldh.modules.order.model.AfterSalesModel;
import com.ldh.modules.order.vo.AfterSalesMerchantDisposeVO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 售后
 * @Author: jeecg-boot
 * @Date:   2022-02-26
 * @Version: V1.0
 */
public interface AfterSalesService extends IService<AfterSales> {

    void applyAfterSales(AfterSales afterSales);

    Page<AfterSalesModel> list(Page page, QueryWrapper queryWrapper, AfterSales afterSales);

    Page<AfterSalesModel> listMerchant(Page page, QueryWrapper queryWrapper, AfterSales afterSales);

    AfterSalesDetailsModel getAfterSalesDetail(String afterSalesId) throws Exception;
    
    void merchantDispose(AfterSalesMerchantDisposeVO afterSalesMerchantDisposeVO);
}
