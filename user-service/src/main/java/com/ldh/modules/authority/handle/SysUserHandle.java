package com.ldh.modules.authority.handle;

import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.modules.authority.service.AuthorityInformationService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags="用户对外接口")
@RestController
@RequestMapping("/user/handle")
public class SysUserHandle {

    @Autowired
    private AuthorityInformationService authorityInformationService;

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

}
