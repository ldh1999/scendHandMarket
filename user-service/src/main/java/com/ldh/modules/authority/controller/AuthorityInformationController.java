package com.ldh.modules.authority.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldh.constant.FilePath;
import com.ldh.modules.authority.entity.AuthorityInformation;
import com.ldh.modules.authority.model.AuthorityInformationModel;
import com.ldh.modules.authority.service.AuthorityInformationService;
import com.ldh.modules.authority.service.AuthorityRoleService;
import com.ldh.modules.authority.service.CosImageService;
import common.Result;
import common.StringTo;
import constant.UploadFileConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Random;

@Slf4j
@Api(tags="用户模块")
@RestController
@RequestMapping("User/AuthorityInformation")
public class AuthorityInformationController {

    @Autowired
    private AuthorityInformationService authorityInformationService;

    @Autowired
    private AuthorityRoleService authorityRoleService;

    @Autowired
    private CosImageService cosImageService;

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
            authorityInformationService.deleteAnyOneById(id);
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("删除失败");
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



    @ApiOperation(value="头像更新", notes="头像更新")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(name = "id") String id
    ){
        Result<String> result = new Result<>();
        try{
            AuthorityInformation temp = authorityInformationService.getById(id);
            cosImageService.deleteByPath(temp.getImgPath());
            String saveDbPath = cosImageService.uploadImage(file);
            AuthorityInformation authorityInformation = new AuthorityInformation();
            authorityInformation.setAuthorityId(id);
            authorityInformation.setImgPath(saveDbPath);
            authorityInformationService.updateById(authorityInformation);
            result.setResult(saveDbPath);
            result.setMessage("上传成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.error("上传失败");
        }
        return result;
    }
}
