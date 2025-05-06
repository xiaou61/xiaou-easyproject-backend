package com.xiaou.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 本地上传
     */
    String uploadLocal(MultipartFile file);


    /**
     * 腾讯云 COS 上传
     */
    String uploadCOS(MultipartFile file);

    /**
     * 阿里云 OSS 上传
     */
    String uploadOSS(MultipartFile file);

    /**
     * 华为云 OBS 上传
     */
    String uploadOBS(MultipartFile file);
}
