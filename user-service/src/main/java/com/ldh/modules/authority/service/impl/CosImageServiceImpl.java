package com.ldh.modules.authority.service.impl;

import com.ldh.modules.authority.service.CosImageService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import key.TengXun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class CosImageServiceImpl implements CosImageService {

    @Autowired
    private COSClient cosClient;

    @Override
    @Transactional
    public String uploadImage(MultipartFile file) throws Exception {
        if (file.isEmpty()){
            throw new Exception("file null");
        }
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
