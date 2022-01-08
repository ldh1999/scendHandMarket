package com.ldh.modules.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.OrderPhysicalDistribution;
import com.ldh.modules.order.service.OrderPhysicalDistributionService;
import common.Result;
import common.StringTo;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 订单物流
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="订单物流")
@RestController
@RequestMapping("/order/orderPhysicalDistribution")
public class OrderPhysicalDistributionController{
	@Autowired
	private OrderPhysicalDistributionService orderPhysicalDistributionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param orderPhysicalDistribution
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value="订单物流-分页列表查询", notes="订单物流-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(OrderPhysicalDistribution orderPhysicalDistribution,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="column", required = false) String column,
								   @RequestParam(name="order", required = false) String order) {
		QueryWrapper queryWrapper = new QueryWrapper();
		if(order != null && order.equals("desc")){
			queryWrapper.orderByDesc(StringTo.humpToLine(column));
		}else{
			queryWrapper.orderByAsc(StringTo.humpToLine(column));
		}
		return Result.OK();
	}
	
	/**
	 * 添加
	 *
	 * @param orderPhysicalDistribution
	 * @return
	 */
	@ApiOperation(value="订单物流-添加", notes="订单物流-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody OrderPhysicalDistribution orderPhysicalDistribution) {
		orderPhysicalDistributionService.save(orderPhysicalDistribution);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param orderPhysicalDistribution
	 * @return
	 */
	@ApiOperation(value="订单物流-编辑", notes="订单物流-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody OrderPhysicalDistribution orderPhysicalDistribution) {
		orderPhysicalDistributionService.updateById(orderPhysicalDistribution);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="订单物流-通过id删除", notes="订单物流-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		orderPhysicalDistributionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="订单物流-批量删除", notes="订单物流-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.orderPhysicalDistributionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="订单物流-通过id查询", notes="订单物流-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		OrderPhysicalDistribution orderPhysicalDistribution = orderPhysicalDistributionService.getById(id);
		return Result.OK(orderPhysicalDistribution);
	}

}
