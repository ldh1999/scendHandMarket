package com.ldh.modules.order.controller;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.order.entity.AfterSales;
import com.ldh.modules.order.entity.OrderInformation;
import com.ldh.modules.order.service.AfterSalesService;
import com.ldh.util.RedisSessionUtil;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Description: 售后
 * @Author: jeecg-boot
 * @Date:   2022-02-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="售后")
@RestController
@RequestMapping("/order/afterSales")
public class AfterSalesController{

	@Autowired
	private AfterSalesService afterSalesService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 申请售后
	 *
	 * @param afterSales
	 * @return
	 */
	@ApiOperation(value="售后-申请售后", notes="售后-申请售后")
	@PostMapping(value = "/applyAfterSales")
	public Result<?> add(@RequestBody AfterSales afterSales) {
		Result result = new Result();
		try {
			afterSales.setAfterSalesNo(UUID.randomUUID().toString());
			afterSalesService.applyAfterSales(afterSales);
			result.succcess("申请成功");
			result.setResult(afterSales);
		}catch (Exception e){
			result.error("申请失败");
			log.error(e.getMessage(), e);
		}
		return result;
	}

	 /**
	  * 获取当前登陆人正在处理的售后
	  *
	  * @param
	  * @return
	  */
	 @ApiOperation(value="售后-获取当前登陆人正在处理的售后", notes="售后-获取当前登陆人正在处理的售后")
	 @GetMapping(value = "/getAfterByNowUser")
	 public Result<?> getAfterByNowUser(AfterSales afterSales,
										@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										@RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
		 Result result = new Result();
		 Page<OrderInformation> page = new Page<>(pageNo, pageSize);
		 try {
			 HttpSession session = request.getSession();
			 AuthorityInformation authorityInformation = (AuthorityInformation) RedisSessionUtil.sessionAttributeToEntity(session.getAttribute("user"), AuthorityInformation.class);
			 afterSales.setCreateBy(authorityInformation.getAuthorityId());
			 result.setResult(afterSalesService.list(page, new QueryWrapper(), afterSales));
		 }catch (Exception e){
			 result.error(e.getMessage());
			 log.error(e.getMessage(), e);
		 }
		 return result;
	 }



	
	/**
	 * 编辑
	 *
	 * @param afterSales
	 * @return
	 */
	@ApiOperation(value="售后-编辑", notes="售后-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AfterSales afterSales) {
		afterSalesService.updateById(afterSales);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="售后-通过id删除", notes="售后-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		afterSalesService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="售后-通过id查询", notes="售后-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AfterSales afterSales = afterSalesService.getById(id);
		return Result.OK(afterSales);
	}


}
