package com.xiaou.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 上传文件并返回可访问地址
     */
    String uploadLocal(MultipartFile file);
}
