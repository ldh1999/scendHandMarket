package com.ldh.modules.upload.controller;

import com.ldh.modules.upload.model.ImageGetVO;
import com.ldh.modules.upload.model.ImageListGetVO;
import com.ldh.modules.upload.model.ImageNoteModel;
import com.ldh.modules.upload.service.ImageNoteService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("imageNote/getOut")
@Api("图片对外接口")
public class ImageNoteGetController {

    @Autowired
    private ImageNoteService imageNoteService;


    @ApiOperation(value="根据object和type获取", notes="根据object和type获取")
    @RequestMapping(path = "getByObjectIdAndImgGroup", method = RequestMethod.GET)
    public Result<?> getByObjectIdAndImgGroup(ImageGetVO imageGetVO,
                                              ServletRequest request){
        Result<List<ImageNoteModel>> result = new Result();
        try{
            if (!imageGetVO.isNotNull()){
                throw new Exception("");
            }
            List<ImageNoteModel> list = imageNoteService.getByObjectIdAndImgGroup(imageGetVO);
            final String url = this.getNowUrl(request);
            list.stream().forEach(e->{
                e.setImgPath(url+e.getImgPath());
            });
            result.setResult(list);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value="根据多个条件反list", notes="根据多个条件反list")
    @RequestMapping(path = "getByObjectIdAndImgGroupList", method = RequestMethod.GET)
    public Result<?> getByObjectIdAndImgGroupList(ImageListGetVO imageListGetVO,
                                                  ServletRequest request){
        Result<List<?>> result = new Result<>();
        try{
            List<ImageNoteModel> list = imageNoteService.getByObjectIdAndImgGroupList(imageListGetVO);
            final String url = this.getNowUrl(request);
            list.stream().forEach(e->{
                if (!StringUtils.isEmpty(e.getImgPath())){
                    e.setImgPath(url+e.getImgPath());
                }
            });
            result.setResult(list);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private String getNowUrl(ServletRequest request){
        return request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort();
    }

}
