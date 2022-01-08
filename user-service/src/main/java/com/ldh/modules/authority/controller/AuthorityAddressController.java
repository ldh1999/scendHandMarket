package com.ldh.modules.authority.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import User.AuthorityInformation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityAddress;
import com.ldh.modules.authority.service.AuthorityAddressService;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.util.RedisSessionUtil;
import common.OptionModel;
import common.Result;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Slf4j
@Api(tags="用户地址")
@RestController
@RequestMapping("/authority/authorityAddress")
public class AuthorityAddressController{
	@Autowired
	private AuthorityAddressService authorityAddressService;
	
	/**
	 * 分页列表查询
	 *
	 * @param authorityAddress
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	/*@ApiOperation(value="用户地址-分页列表查询", notes="用户地址-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> list(AuthorityAddress authorityAddress,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//TODO
		QueryWrapper<?> queryWrapper = new QueryWrapper<>();
		Page<AuthorityAddress> page = new Page<AuthorityAddress>(pageNo, pageSize);
		IPage<AuthorityAddress> pageList = authorityAddressService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
*/
	@ApiOperation(value="用户地址-分页列表查询客户端", notes="用户地址-分页列表查询客户端")
	@GetMapping(value = "/listClient")
	public Result<?> listClient(AuthorityAddress authorityAddress,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		Result<IPage> result = new Result<>();
		Page<AuthorityAddress> page = new Page<AuthorityAddress>(pageNo, pageSize);
		QueryWrapper<?> queryWrapper = new QueryWrapper<>();
		try{
			IPage iPage = authorityAddressService.getListClient(page, queryWrapper, authorityAddress);
			result.setResult(iPage);
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
	 * @param authorityAddress
	 * @return
	 */
	@ApiOperation(value="用户地址-添加", notes="用户地址-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AuthorityAddress authorityAddress) {
		Result<?> result = new Result<>();
		try {
			authorityAddressService.save(authorityAddress);
			result.succcess("增加成功");
		}catch (Exception e){
			log.error(e.getMessage());
			result.error();
		}
		return result;
	}
	
	/**
	 * 编辑
	 *
	 * @param authorityAddress
	 * @return
	 */
	@ApiOperation(value="用户地址-编辑", notes="用户地址-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody AuthorityAddress authorityAddress) {
		Result<?> result = new Result<>();
		try {
			authorityAddressService.updateById(authorityAddress);
			result.succcess("修改成功");
		}catch (Exception e){
			log.error(e.getMessage());
			result.error();
		}
		return result;
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="用户地址-通过id删除", notes="用户地址-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Result<?> result = new Result<>();
		try {
			authorityAddressService.removeById(id);
			result.succcess("删除成功");
		}catch (Exception e){
			log.error(e.getMessage());
			result.error();
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="用户地址-通过id查询", notes="用户地址-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AuthorityAddress authorityAddress = authorityAddressService.getById(id);
		return Result.OK(authorityAddress);
	}

	/**
	 * 获取当前用户地址下拉框
	 * @return
	 */
	@ApiOperation(value="用户地址-获取当前用户地址下拉框", notes="用户地址-获取当前用户地址下拉框")
	@GetMapping(value = "/getNowUserOption")
	public Result<?> getNowUserOption(HttpServletRequest request){
		Result<List<?>> result = new Result<>();
		HttpSession session = request.getSession();
		try{
			AuthorityInformationModel authorityInformation = (AuthorityInformationModel)session.getAttribute("user");
			result.setResult(authorityAddressService.getOptionByUserId(authorityInformation.getAuthorityId()));
		}catch (Exception e){
			log.error(e.getMessage(), e);
			result.succcess(e.getMessage());
		}
		return result;
	}

}
