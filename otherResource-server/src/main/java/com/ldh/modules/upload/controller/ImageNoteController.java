package com.ldh.modules.upload.controller;

import com.ldh.modules.upload.constant.FilePath;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation(value="图片上传", notes="图片上传")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Random random = new Random(10);
        String randomString = String.valueOf(random.nextInt());
        String filePathName = randomString.substring(1,randomString.length())+fileName;
        String savePath = FilePath.FILE_SAVE_PATH+filePathName;
        try{
            File file1 = new File(savePath);
            if (file1.isDirectory()){
                file1.mkdirs();
            }
            file.transferTo(file1);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }


}
