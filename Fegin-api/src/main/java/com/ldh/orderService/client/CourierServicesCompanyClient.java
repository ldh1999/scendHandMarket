package com.ldh.orderService.client;

import com.ldh.orderService.pojo.CourierServicesCompany;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 快递公司维护
* @Author: ldh
* @Date:   2022-01-08
* @Version: V1.0
*/
@FeignClient("orderservice/order/courierServicesCompany/handle")
public interface CourierServicesCompanyClient {


   /**
    * 分页列表查询
    *
    * @param courierServicesCompany
    * @param pageNo
    * @param pageSize
    * @return
    */
   @ApiOperation(value="快递公司维护-分页列表查询", notes="快递公司维护-分页列表查询")
   @GetMapping(value = "/list")
   Result<?> queryPageList(@SpringQueryMap CourierServicesCompany courierServicesCompany,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  @RequestParam(name="column", required = false) String column,
                                  @RequestParam(name="order", required = false) String order) ;

   /**
    * 添加
    *
    * @param courierServicesCompany
    * @return
    */
   @ApiOperation(value="快递公司维护-添加", notes="快递公司维护-添加")
   @PostMapping(value = "/add")
   Result<?> add(@RequestBody CourierServicesCompany courierServicesCompany);

   /**
    * 编辑
    *
    * @param courierServicesCompany
    * @return
    */
   @ApiOperation(value="快递公司维护-编辑", notes="快递公司维护-编辑")
   @PutMapping(value = "/edit")
   Result<?> edit(@RequestBody CourierServicesCompany courierServicesCompany);

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @ApiOperation(value="快递公司维护-通过id删除", notes="快递公司维护-通过id删除")
   @DeleteMapping(value = "/delete")
   Result<?> delete(@RequestParam(name="id",required=true) String id);
   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @ApiOperation(value="快递公司维护-通过id查询", notes="快递公司维护-通过id查询")
   @GetMapping(value = "/queryById")
   Result<?> queryById(@RequestParam(name="id",required=true) String id);

}
