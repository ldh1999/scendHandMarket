package com.ldh.modules.informationMaintenance.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.CourierServicesCompany;
import com.ldh.modules.informationMaintenance.service.CourierServicesCompanyService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
* @Description: 快递公司维护
* @Author: ldh
* @Date:   2022-01-08
* @Version: V1.0
*/
@Slf4j
@Api(tags="快递公司维护")
@RestController
@RequestMapping("/order/courierServicesCompany/handle")
public class CourierServicesCompanyHandle {
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
   @ApiOperation(value="快递公司维护-分页列表查询", notes="快递公司维护-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(CourierServicesCompany courierServicesCompany,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  @RequestParam(name="column", required = false) String column,
                                  @RequestParam(name="order", required = false) String order) {
       Result<Page> result = new Result<>();
       Page page = new Page(pageNo, pageSize);
       QueryWrapper queryWrapper = new QueryWrapper();
       if(order != null && order.equals("desc")){
           queryWrapper.orderByDesc(StringTo.humpToLine(column));
       }else{
           queryWrapper.orderByAsc(StringTo.humpToLine(column));
       }
       try {
           result.setResult(courierServicesCompanyService.list(page, queryWrapper, courierServicesCompany));
           result.setSuccess(true);
       }catch (Exception e){
           log.error(e.getMessage(), e);
           result.error(e.getMessage());
       }
       return result;
   }

   /**
    * 添加
    *
    * @param courierServicesCompany
    * @return
    */
   @ApiOperation(value="快递公司维护-添加", notes="快递公司维护-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody CourierServicesCompany courierServicesCompany) {
       courierServicesCompanyService.save(courierServicesCompany);
       return Result.OK("添加成功！");
   }

   /**
    * 编辑
    *
    * @param courierServicesCompany
    * @return
    */
   @ApiOperation(value="快递公司维护-编辑", notes="快递公司维护-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody CourierServicesCompany courierServicesCompany) {
       courierServicesCompanyService.updateById(courierServicesCompany);
       return Result.OK("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @ApiOperation(value="快递公司维护-通过id删除", notes="快递公司维护-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       courierServicesCompanyService.removeById(id);
       return Result.OK("删除成功!");
   }
   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @ApiOperation(value="快递公司维护-通过id查询", notes="快递公司维护-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       CourierServicesCompany courierServicesCompany = courierServicesCompanyService.getById(id);
       return Result.OK(courierServicesCompany);
   }

}
