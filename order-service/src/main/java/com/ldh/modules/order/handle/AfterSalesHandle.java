package com.ldh.modules.order.handle;


import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.model.AfterSalesModel;
import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order/afterSales/handle")
@Api(tags="售后对外接口")
@Slf4j
public class AfterSalesHandle {


    @Autowired
    private AfterSalesService afterSalesService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取全部售后
     *
     * @param
     * @return
     */
    @ApiOperation(value="售后-获取全部售后", notes="售后-获取全部售后")
    @GetMapping(value = "/getAllAfterSales")
    public Result<Page<AfterSalesModel>> getAfterByNowUser(AfterSales afterSales,
                                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                     @RequestParam(name = "column", required = false) String column,
                                                     @RequestParam(name = "order", required = false) String order){
        Result result = new Result();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(order)){
            if (order.equals("desc")) {
                queryWrapper.orderByDesc(StringTo.humpToLine(column));
            } else {
                queryWrapper.orderByAsc(StringTo.humpToLine(column));
            }
        }
        Page<OrderInformation> page = new Page<>(pageNo, pageSize);
        try {
            result.setResult(afterSalesService.list(page, queryWrapper, afterSales));
        }catch (Exception e){
            result.error(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @ApiOperation(value="售后-获取详情信息", notes="售后-获取详情信息")
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
