package com.ldh.modules.informationMaintenance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.Courier;
import com.ldh.modules.informationMaintenance.service.ICourierService;
import common.Result;
import common.StringTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 快递员
 * @Author: jeecg-boot
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="快递员")
@RestController
@RequestMapping("/order/courier")
public class CourierController {
	@Autowired
	private ICourierService courierService;
	
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
	public Result<?> queryPageList(Courier courier,
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
			result.setResult(courierService.list(page, queryWrapper, courier));
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
	 * @param courier
	 * @return
	 */
	@ApiOperation(value="快递员-添加", notes="快递员-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Courier courier) {
		Result result = new Result();
		try {
			courierService.addHandle(courier);
			result.succcess("添加成功");
			result.setSuccess(true);
		}catch (Exception e){
			log.error(e.getMessage(), e);
			result.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 编辑
	 *
	 * @param courier
	 * @return
	 */
	@ApiOperation(value="快递员-编辑", notes="快递员-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Courier courier) {
		courierService.updateById(courier);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="快递员-通过id删除", notes="快递员-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Result result = new Result();
		try {
			courierService.deleteAnyById(id);
			result.succcess("删除成功");
		}catch (Exception e){
			log.error(e.getMessage(), e);
			result.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="快递员-通过id查询", notes="快递员-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Courier courier = courierService.getById(id);
		return Result.OK(courier);
	}

}
