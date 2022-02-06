package com.ldh.modules.shop.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import com.ldh.modules.shop.entity.ShopPreferencesType;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 购物偏好
 * @Author: ldh
 * @Date:   2022-02-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="购物偏好")
@RestController
@RequestMapping("/inventory/shopPreferencesType")
public class ShopPreferencesTypeController{
	@Autowired
	private ShopPreferencesTypeService shopPreferencesTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param shopPreferencesType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="购物偏好-分页列表查询", notes="购物偏好-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ShopPreferencesType shopPreferencesType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		return Result.OK();
	}
	
	/**
	 * 添加
	 *
	 * @param shopPreferencesType
	 * @return
	 */
	@ApiOperation(value="购物偏好-添加", notes="购物偏好-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ShopPreferencesType shopPreferencesType) {
		shopPreferencesTypeService.save(shopPreferencesType);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param shopPreferencesType
	 * @return
	 */
	@ApiOperation(value="购物偏好-编辑", notes="购物偏好-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ShopPreferencesType shopPreferencesType) {
		shopPreferencesTypeService.updateById(shopPreferencesType);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="购物偏好-通过id删除", notes="购物偏好-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		shopPreferencesTypeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="购物偏好-批量删除", notes="购物偏好-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shopPreferencesTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="购物偏好-通过id查询", notes="购物偏好-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ShopPreferencesType shopPreferencesType = shopPreferencesTypeService.getById(id);
		return Result.OK(shopPreferencesType);
	}

}
