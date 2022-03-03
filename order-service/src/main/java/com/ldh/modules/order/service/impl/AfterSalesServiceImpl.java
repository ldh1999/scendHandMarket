package com.ldh.modules.order.service.impl;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.mapper.AfterSalesMapper;
import com.ldh.modules.order.model.AfterSalesModel;
import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.util.RedisSessionUtil;
import constant.AfterSalesDisposeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.beancontext.BeanContext;


/**
 * @Description: 售后
 * @Author: jeecg-boot
 * @Date:   2022-02-26
 * @Version: V1.0
 */
@Service
public class AfterSalesServiceImpl extends ServiceImpl<AfterSalesMapper, AfterSales> implements AfterSalesService {

    @Autowired
    private OrderInformationService orderInformationService;

    @Autowired
    private AfterSalesMapper afterSalesMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public void applyAfterSales(AfterSales afterSales) {

        HttpSession session = request.getSession();
        AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);

        //更改订单售后状态
        OrderInformation orderInformation = new OrderInformation();
        orderInformation
                .setOrderId(afterSales.getOrderId())
                .setIsAfterSales("1");
        orderInformationService.updateById(orderInformation);

        //创建售后单
        OrderInformation orderInformationTemp = orderInformationService.getById(afterSales.getOrderId());
        afterSales
                .setMerchantId(orderInformationTemp.getMerchantId())
                .setInventoryId(orderInformationTemp.getInventoryId())
                .setCreateBy(authorityInformation.getAuthorityId())
                .setSts("ing")
                .setAfterSalesSts(AfterSalesDisposeEnum.wait_merchant.getSts());
        this.save(afterSales);
    }

    @Override
    public Page<AfterSalesModel> list(Page page, QueryWrapper queryWrapper, AfterSales afterSales) {
        return afterSalesMapper.list(page, queryWrapper,afterSales);
    }
}
