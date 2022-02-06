package com.ldh.modules.order.controller;
import com.ldh.modules.order.service.CourierService;
import com.ldh.orderService.pojo.Courier;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 快递员
* @Author: ldh
* @Date:   2022-01-11
* @Version: V1.0
*/
@Slf4j
@Api(tags="快递员")
@RestController
@RequestMapping("/order/courier")
public class CourierController {
   @Autowired
   private CourierService courierService;

   /**
    * 分页列表查询
    *
    * @param courier
    * @param pageNo
    * @param pageSize
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递员-分页列表查询", notes="快递员-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(Courier courier,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  @RequestParam(name="column", required = false) String column,
                                  @RequestParam(name="order", required = false) String order) {
       return courierService.queryPageList(courier, pageNo, pageSize, column, order);
   }

   /**
    * 添加
    *
    * @param courier
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递员-添加", notes="快递员-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody Courier courier) {
       return courierService.add(courier);
   }

   /**
    * 编辑
    *
    * @param courier
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递员-编辑", notes="快递员-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody Courier courier) {
       return courierService.edit(courier);
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @PreAuthorize("hasAuthority('admin')")
   @ApiOperation(value="快递员-通过id删除", notes="快递员-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       return courierService.delete(id);
   }
}
