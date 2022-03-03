package com.ldh.modules.order.controller;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.OrderInformationModel;
import com.ldh.modules.order.model.PhysicalDetailModel;
import com.ldh.modules.order.service.OrderInformationService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import common.StringTo;
import constant.OrderEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
@Api(tags="客户端订单")
@RestController
@RequestMapping("/order/orderClient")
public class OrderClientController {

    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 卖家订单分页列表查询
     *
     * @param orderInformation
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="买家订单-分页列表查询", notes="订单信息表-分页列表查询")
    @GetMapping(value = "/buyerList")
    public Result<?> buyerList(OrderInformation orderInformation,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize
                               ) {
        Page<OrderInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        Result result = new Result();
        try{
            IPage<OrderInformationModel> pageList = orderInformationService.listToBuyer(page, queryWrapper, orderInformation);
            result.setResult(pageList);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 用户下单
     *
     * @param orderInformation
     * @return
     */
    @ApiOperation(value="订单信息表-添加", notes="订单信息表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody OrderInformation orderInformation, HttpServletRequest request) {
        Result result = new Result();
        HttpSession session = request.getSession();
        try{
            AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
            orderInformation.setCreateBy(authorityInformation.getAuthorityId());
            orderInformation.setOrderCode(UUID.randomUUID().toString());
            orderInformationService.save(orderInformation);
            result.setResult(orderInformation);
            result.setSuccess(true);
            result.setMessage("订单增加成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="买家订单-确认收货", notes="订单信息表-确认收货")
    @GetMapping(value = "/orderEnd")
    public Result<?> orderEnd(@RequestParam(value = "orderId", required = true) String id){
        Result result = new Result();
        try {
            orderInformationService.orderEnd(id);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    /**
     * 根据订单id获取物流信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据订单id获取物流信息", notes="根据订单id获取物流信息")
    @GetMapping(value = "/getPhysicalDetail")
    public Result<PhysicalDetailModel> getPhysicalDetail(@RequestParam(value = "orderId", required = true) String id){
        Result result = new Result();
        try {
            result.setResult(orderInformationService.getPhysicalDetail(id));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    public Result<?> order(@RequestParam(value = "orderId", required = true) String id){return null;}
}
