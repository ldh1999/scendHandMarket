package com.ldh.modules.merchant.service.impl;

import com.ldh.modules.merchant.service.CosImageService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import constant.DefaultPath;
import key.TengXun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

    @Override
    public void deleteByPath(String path) {
        if (StringUtils.isEmpty(path) || DefaultPath.DEFAULT_USER_IMG.equals(path))
            return;
        String[] paths = path.split("/");
        String key = paths[paths.length-1];
        cosClient.deleteObject(TengXun.bucketName, key);
    }
}
