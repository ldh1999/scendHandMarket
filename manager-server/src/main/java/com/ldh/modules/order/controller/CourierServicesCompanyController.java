package com.ldh.modules.order.controller;

import com.ldh.modules.order.service.CourierServicesCompanyService;
import com.ldh.orderService.pojo.CourierServicesCompany;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 快递公司维护
* @Author: ldh
* @Date:   2022-01-08
* @Version: V1.0
*/
@Slf4j
@Api(tags="快递公司维护")
@RestController
@RequestMapping("/order/courierServicesCompany")
public class CourierServicesCompanyController {

   @Autowired
   private CourierServicesCompanyService courierServicesCompanyService;

   /**
    * 分页列表查询
    *
    * @param courierServicesCompany
    * @param pageNo
    * @param pageSize
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递公司维护-分页列表查询", notes="快递公司维护-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(CourierServicesCompany courierServicesCompany,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  @RequestParam(name="column", required = false) String column,
                                  @RequestParam(name="order", required = false) String order) {
       return courierServicesCompanyService.queryPageList(courierServicesCompany, pageNo, pageSize, column, order);
   }

   /**
    * 添加
    *
    * @param courierServicesCompany
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递公司维护-添加", notes="快递公司维护-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody CourierServicesCompany courierServicesCompany) {
       return courierServicesCompanyService.add(courierServicesCompany);
   }

   /**
    * 编辑
    *
    * @param courierServicesCompany
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递公司维护-编辑", notes="快递公司维护-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody CourierServicesCompany courierServicesCompany) {
       return courierServicesCompanyService.edit(courierServicesCompany);
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递公司维护-通过id删除", notes="快递公司维护-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       return courierServicesCompanyService.delete(id);
   }
}
