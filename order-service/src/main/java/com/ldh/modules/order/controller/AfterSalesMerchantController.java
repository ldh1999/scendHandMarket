package com.ldh.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.AfterSalesModel;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.service.AfterSalesService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/order/afterSalesMerchant")
@Api(tags="商家售后")
@Slf4j
public class AfterSalesMerchantController {

    @Autowired
    private AfterSalesService afterSalesService;

    @Autowired
    private HttpServletRequest request;



    @ApiOperation(value="商家售后-查看当前售后情况", notes="商家售后-查看当前售后情况")
    @GetMapping(value = "/list")
    public Result<?> add(AfterSales afterSales,
                         @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                         @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                         @RequestParam(name = "column", required = false) String column,
                         @RequestParam(name = "order", required = false) String order) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(order)){
            if (order.equals("desc")) {
                queryWrapper.orderByDesc(StringTo.humpToLine(column));
            } else {
                queryWrapper.orderByAsc(StringTo.humpToLine(column));
            }
        }
        Page<AfterSales> page = new Page<>(pageNo, pageSize);
        Result<Page> result = new Result();
        try {
            result.setResult(afterSalesService.listMerchant(page, queryWrapper, afterSales));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


    @ApiOperation(value="商家售后-获取详情信息", notes="商家售后-获取详情信息")
    @GetMapping(value = "/getAfterSalesDetail")
    public Result<?> getAfterSalesDetail(@RequestParam(name = "afterSalesId", required = true) String afterSalesId) {
        Result result = new Result();
        try {
            result.setResult(afterSalesService.getAfterSalesDetail(afterSalesId));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }
}
