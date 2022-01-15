package com.ldh.modules.order.handle;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.OrderPhysicalDistributionModel;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import com.ldh.modules.order.vo.OrderPhysicalDistributionVO;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Executable;

@Slf4j
@Api(tags="订单物流")
@RestController
@RequestMapping("/order/orderPhysicalDistribution/handle")
public class OrderPhysicalDistributionHandle {

    @Autowired
    private OrderPhysicalDistributionService orderPhysicalDistributionService;

    /**
     * 快递员接单列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="快递员接单列表", notes="快递员接单列表")
    @GetMapping(value = "/acceptList")
    public Result<?> acceptList(OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order) {
        Page<OrderPhysicalDistribution> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result result = new Result();
        try{
            IPage<OrderPhysicalDistributionModel> pageList = orderPhysicalDistributionService.listAccept(page, queryWrapper, orderPhysicalDistributionVO);
            result.setResult(pageList);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 快递员工作台
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="快递员工作台", notes="快递员工作台")
    @GetMapping(value = "/listWork")
    public Result<?> listWork(OrderPhysicalDistributionVO orderPhysicalDistributionVO,
                                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                @RequestParam(name="column", required = false) String column,
                                @RequestParam(name="order", required = false) String order) {
        Page<OrderPhysicalDistribution> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result result = new Result();
        try{
            IPage<OrderPhysicalDistributionModel> pageList = orderPhysicalDistributionService.listWork(page, queryWrapper, orderPhysicalDistributionVO);
            result.setResult(pageList);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


    @ApiOperation(value="快递员接单", notes="快递员接单")
    @GetMapping(value = "/acceptCourier")
    public Result<?> acceptCourier(@RequestParam(value = "orderPhysicalDistributionId", required = true) String orderPhysicalDistributionId,
                                   @RequestParam(value = "courierCode", required = true) String courierCode){
        Result result = new Result();
        try {
            orderPhysicalDistributionService.acceptCourier(orderPhysicalDistributionId, courierCode);
            result.succcess("接单成功");
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }

        return result;
    }

}
