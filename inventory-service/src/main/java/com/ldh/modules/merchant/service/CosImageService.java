package com.ldh.modules.merchant.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface CosImageService {

    /**
     * 将文件上传入腾讯cos
     * @param file 需要上传的文件
     * @return 网络路径
     * @throws IOException
     */
    String uploadImage(MultipartFile file) throws Exception;

    void deleteByPath(String path);
}
