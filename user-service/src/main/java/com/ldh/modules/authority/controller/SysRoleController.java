package com.ldh.modules.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.SysRole;
import com.ldh.modules.authority.service.SysRoleService;
import common.OptionModel;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "User/Role")
@Api("用户权限管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(path = "list",method = RequestMethod.GET)
    public Result<?> list(SysRole sysRole,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Result<IPage<SysRole>> result = new Result<>();
        Page<SysRole> page = new Page<>(pageNo,pageSize);
        QueryWrapper<AuthorityInformation> queryWrapper =  new QueryWrapper<>();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        try{
            result.setResult(sysRoleService.list(page, queryWrapper, sysRole));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @RequestMapping(path = "delete" , method = RequestMethod.DELETE)
    public Result<?> delete(@RequestParam(name="id")String id, HttpServletRequest request){
        Result<?> result = new Result<>();
        try{
            sysRoleService.deleteByIdScan(id, request);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "add" , method = RequestMethod.POST)
    public Result<?> add(@RequestBody SysRole sysRole){
        Result<?> result = new Result<>();
        try{
            if (sysRoleService.countByNo(sysRole)>0){
                result.error("该编码已存在");
                return result;
            }
            sysRoleService.save(sysRole);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @RequestMapping(path = "updateById" , method = RequestMethod.POST)
    public Result<?> update(@RequestBody SysRole sysRole){
        Result<?> result = new Result<>();
        try{
            if (sysRoleService.countByNo(sysRole)>0){
                result.error("该编码已存在");
                return result;
            }
            sysRoleService.updateById(sysRole);
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

    @ApiOperation(value="获取权限下拉框", notes="获取权限下拉框")
    @RequestMapping(path = "/getRoleAllOption", method = RequestMethod.GET)
    public Result<?> getRoleAllOption(){
        Result<List<OptionModel>> result = new Result<>();
        try{
            result.setResult(sysRoleService.getAllOption());
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("error");
        }
        return result;
    }

}
