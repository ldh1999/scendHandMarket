package com.ldh.modules.upload.controller;

import com.ldh.modules.upload.constant.FilePath;
import com.ldh.modules.upload.entity.ImageNote;
import com.ldh.modules.upload.service.ImageNoteService;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("image/note")
@Api("图片操作")
public class ImageNoteController {

    @Autowired
    private ImageNoteService imageNoteService;

    @ApiOperation(value="图片上传", notes="图片上传")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(name = "imageGroup") String imageGroup,
                                 @RequestParam(name = "objectId") String objectId){

        Result<?> result = new Result<>();

        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Random random = new Random();
        String randomString = String.valueOf(random.nextInt());
        String fileSaveName = randomString.substring(1,randomString.length());
        String filePathName = fileSaveName+fileName;
        String savePath = FilePath.IMAGE_SAVE_PATH+imageGroup+"/"+filePathName;
        String saveDbPath = FilePath.IMAGE_SHOW_PATH+imageGroup+"/"+filePathName;
        if (!fileType.substring(0,fileType.indexOf("/")).equals("image")){
            result.error("请上传图片");
            return result;
        }
        try{
            File file1 = new File(savePath);
            if (!file1.isDirectory()){
                file1.mkdirs();
            }
            file.transferTo(file1);
            ImageNote imageNote = new ImageNote();
            imageNote.setImgGroup(imageGroup)
                    .setImgName(fileName)
                    .setImgPath(saveDbPath)
                    .setObjectId(objectId);
            imageNoteService.save(imageNote);
            result.succcess("上传成功");
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.error("上传失败");
        }
        return result;
    }




}
