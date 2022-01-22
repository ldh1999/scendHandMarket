package com.ldh.orderService.client;

import com.ldh.orderService.pojo.Courier;
import common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient("orderservice/order/courier/handle")
public interface CourierClient {

    /**
     * 分页列表查询
     *
     * @param courier
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value="快递员-分页列表查询", notes="快递员-分页列表查询")
    @GetMapping(value = "/list")
    Result<?> queryPageList(@SpringQueryMap Courier courier,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   @RequestParam(name="column", required = false) String column,
                                   @RequestParam(name="order", required = false) String order);

    /**
     * 添加
     *
     * @param courier
     * @return
     */
    @ApiOperation(value="快递员-添加", notes="快递员-添加")
    @PostMapping(value = "/add")
    Result<?> add(@RequestBody Courier courier);

    /**
     * 编辑
     *
     * @param courier
     * @return
     */
    @ApiOperation(value="快递员-编辑", notes="快递员-编辑")
    @PutMapping(value = "/edit")
    Result<?> edit(@RequestBody Courier courier);

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value="快递员-通过id删除", notes="快递员-通过id删除")
    @DeleteMapping(value = "/delete")
    Result<?> delete(@RequestParam(name="id",required=true) String id);

    @GetMapping(value = "/queryAllByCode")
    Result<?> queryAllByCode(@RequestParam("code")String code);

}
