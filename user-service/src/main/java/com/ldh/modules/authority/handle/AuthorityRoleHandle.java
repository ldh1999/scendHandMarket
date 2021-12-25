package com.ldh.modules.authority.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.model.AuthorityRoleInformationModel;
import com.ldh.modules.authority.service.AuthorityRoleService;
import common.Result;
import common.StringTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("用户权限表对外接口")
@RequestMapping("User/UserRole/handle")
public class AuthorityRoleHandle {
    @Autowired
    private AuthorityRoleService authorityRoleService;

    @ApiOperation(value="用户权限列表", notes="用户权限列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Result<?> list(AuthorityRoleInformation authorityInformation,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                          @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                          @RequestParam(name="column", required = false) String column,
                          @RequestParam(name="order", required = false) String order){
        Page<AuthorityRoleInformation> page = new Page<>(pageNo, pageSize);
        QueryWrapper<AuthorityInformation> queryWrapper =  new QueryWrapper<>();
        if(order.equals("desc")){
            queryWrapper.orderByDesc(StringTo.humpToLine(column));
        }else{
            queryWrapper.orderByAsc(StringTo.humpToLine(column));
        }
        Result<IPage<AuthorityRoleInformationModel>> result = new Result<>();
        try{
            IPage<AuthorityRoleInformationModel> ipage = authorityRoleService.list(page,queryWrapper,authorityInformation);
            result.succcess(ipage);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="用户权限添加", notes="用户权限添加")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Result<?> insert(@RequestBody AuthorityRoleInformation authorityRoleInformation){
        Result<?> result = new Result<>();
        try {
            if (authorityRoleService.countAuthorityRoleByAuthorityId(authorityRoleInformation.getAuthorityId(), authorityRoleInformation.getSysRoleId())>0){
                result.error("此权限已存在");
                return result;
            }
            authorityRoleService.save(authorityRoleInformation);
            result.succcess("增加成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error();
        }
        return result;
    }
    @ApiOperation(value="用户权限删除", notes="用户权限删除")
    @RequestMapping(path = "/deleteById", method = RequestMethod.DELETE)
    public Result<?> updateById(@RequestParam(value = "id", required = true)String id){

        Result<?> result = new Result<>();
        try{
            authorityRoleService.removeById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
        }
        return result;
    }
}
