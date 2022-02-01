package com.ldh.modules.upload.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import key.TengXun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("test")
public class testController {

    @Autowired
    private COSClient cosClient;

    @PostMapping("test")
    public String test(@RequestParam(value = "file")MultipartFile file) throws IOException {
        // 指定文件将要存放的存储桶
        String bucketName = TengXun.bucketName;
        String type = file.getContentType();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(type);
        String fileName = file.getOriginalFilename();
        String key = UUID.randomUUID().toString() + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String imagePath = "https://" + bucketName + ".cos." + TengXun.RegionName + ".myqcloud.com/" + key;
        return imagePath;
    }
}
