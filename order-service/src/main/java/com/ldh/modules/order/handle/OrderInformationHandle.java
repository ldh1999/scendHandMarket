package com.ldh.modules.order.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationDetailModel;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.service.OrderInformationService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @Description: 订单信息表
 * @Author: ldh
 * @Date: 2021-11-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "订单信息表对外接口")
@RestController
@RequestMapping("/order/orderInformation/handle")
public class OrderInformationHandle {
    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 分页列表查询
     *
     * @param orderInformation
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "订单信息表-分页列表查询", notes = "订单信息表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(OrderInformation orderInformation,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "column", required = false) String column,
                                   @RequestParam(name = "order", required = false) String order) {
        Page<OrderInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (order.equals("desc")) {
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        } else {
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result result = new Result();
        try {
            IPage<OrderInformationModel> pageList = orderInformationService.list(page, queryWrapper, orderInformation);
            result.setResult(pageList);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }


    /**
     * 编辑
     *
     * @param orderInformation
     * @return
     */
    @ApiOperation(value = "订单信息表-编辑", notes = "订单信息表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody OrderInformation orderInformation) {
        orderInformationService.updateById(orderInformation);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "订单信息表-通过id删除", notes = "订单信息表-通过id删除")
    @DeleteMapping(value = "/deleteById")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        orderInformationService.removeById(id);
        return Result.OK("删除成功!");
    }
    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "订单信息表-通过id查询", notes = "订单信息表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        OrderInformation orderInformation = orderInformationService.getById(id);
        return Result.OK(orderInformation);
    }

    /**
     * 通过id查询详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "订单信息表-通过id查询详情", notes = "订单信息表-通过id查询详情")
    @GetMapping(value = "/queryByIdDetail")
    public Result<?> queryByIdDetail(@RequestParam(name = "id", required = true) String id) {
        Result<OrderInformationDetailModel> result = new Result<>();
        try {
            result.setResult(orderInformationService.getByIdDetail(id));
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 根据条件获取order数量
     *
     * @param obj
     * @return
     */
    @ApiOperation(value = "订单信息表-根据条件获取order数量", notes = "订单信息表-根据条件获取order数量")
    @GetMapping(value = "/getOrderCountByObject")
    public Result<?> getOrderCountByObject(@RequestParam(name = "id", required = false) String obj) {
        return Result.OK(orderInformationService.getOrderCountByObject(obj));
    }
}
