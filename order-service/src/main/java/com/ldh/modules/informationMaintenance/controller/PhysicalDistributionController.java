package com.ldh.modules.informationMaintenance.controller;

import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.informationMaintenance.entity.PhysicalDistribution;
import com.ldh.modules.informationMaintenance.service.IPhysicalDistributionService;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 物流信息
 * @Author: ldh
 * @Date:   2022-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="物流信息")
@RestController
@RequestMapping("/order/physicalDistribution")
public class PhysicalDistributionController{
	@Autowired
	private IPhysicalDistributionService physicalDistributionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param physicalDistribution
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="物流信息-分页列表查询", notes="物流信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PhysicalDistribution physicalDistribution,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Page<PhysicalDistribution> page = new Page<PhysicalDistribution>(pageNo, pageSize);
		return Result.OK();
	}
	
	/**
	 * 添加
	 *
	 * @param physicalDistribution
	 * @return
	 */
	@ApiOperation(value="物流信息-添加", notes="物流信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PhysicalDistribution physicalDistribution) {
		physicalDistributionService.save(physicalDistribution);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param physicalDistribution
	 * @return
	 */
	@ApiOperation(value="物流信息-编辑", notes="物流信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PhysicalDistribution physicalDistribution) {
		physicalDistributionService.updateById(physicalDistribution);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="物流信息-通过id删除", notes="物流信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		physicalDistributionService.removeById(id);
		return Result.OK("删除成功!");
	}

	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="物流信息-通过id查询", notes="物流信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PhysicalDistribution physicalDistribution = physicalDistributionService.getById(id);
		return Result.OK(physicalDistribution);
	}

}
