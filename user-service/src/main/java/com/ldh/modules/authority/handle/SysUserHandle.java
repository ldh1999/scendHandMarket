package com.ldh.modules.authority.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.modules.authority.service.AuthorityInformationService;
import com.ldh.modules.authority.service.AuthorityRoleService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api(tags="用户对外接口")
@RestController
@RequestMapping("/user/handle")
public class SysUserHandle {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @Autowired
    private AuthorityRoleService authorityRoleService;

    @ApiOperation(value="用户管理列表", notes="用户管理列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(AuthorityInformation authorityInformation,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Page<AuthorityInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AuthorityInformation> queryWrapper =  new QueryWrapper<>();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result<IPage<AuthorityInformationModel>> result = new Result<>();
        try{
            IPage<AuthorityInformationModel> ipage = authorityInformationService.list(authorityInformation,page,queryWrapper);
            result.succcess(ipage);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="用户管理添加", notes="用户管理添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody AuthorityInformation authorityInformation){
        Result<?> result = new Result<>();
        try {
            if (authorityInformationService.countUserName(authorityInformation)>0){
                result.error("该用户已存在");
                return result;
            }
            authorityInformationService.save(authorityInformation);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="用户管理修改", notes="用户管理修改")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public Result<?> updateById(@RequestBody AuthorityInformation authorityInformation){
        Result<AuthorityInformation> result = new Result<>();
        try {
            if (authorityInformationService.countUserName(authorityInformation)>0){
                result.error("该用户已存在");
                return result;
            }
            authorityInformationService.updateById(authorityInformation);
            result.setResult(authorityInformationService.getById(authorityInformation.getAuthorityId()));
            result.succcess("修改成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }

    @ApiOperation(value="用户管理删除", notes="用户管理删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> updateById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            authorityInformationService.removeById(id);
            authorityRoleService.deleteByAuthorityId(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }


    @RequestMapping(value = "/selectByIds", method = RequestMethod.GET)
    public Result<?> selectByIds(@RequestParam("ids") String ids){
        Result<List<?>> result = new Result();
        try {
            String[] str = ids.split(",");
            result.setResult(authorityInformationService.selectByIds(str));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="用户管理查找", notes="用户管理查找")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Result<?> selectById(@RequestParam(value = "id", required = true)String id){
        Result<AuthorityInformation> result = new Result<>();
        try{
            result.setResult(authorityInformationService.getById(id));
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("操作失败");
        }
        return result;
    }

    @ApiOperation(value="用户管理查找根据用户名", notes="用户管理查找根据用户名")
    @RequestMapping(path = "/selectByUserName", method = RequestMethod.GET)
    public Result<?> selectByUserName(@RequestParam(value = "username", required = true)String username, HttpServletRequest request){
        Result<AuthorityInformationModel> result = new Result<>();
        try{
            result.setResult(authorityInformationService.findByUserName(username,request));
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("操作失败");
        }
        return result;
    }

    /**
     * 根据obj获取用户数量
     * @param obj
     * @return
     */
    @RequestMapping(value = "/getUserCountByObject", method = RequestMethod.GET)
    public Result<?> getUserCountByObject(@RequestParam(name = "obj", required = false) String obj){
        Result<Integer> result = new Result();
        try {
            result.setResult(authorityInformationService.getUserCountByObject(obj));
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

}
