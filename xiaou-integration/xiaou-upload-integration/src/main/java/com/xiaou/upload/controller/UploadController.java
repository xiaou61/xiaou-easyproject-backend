package com.xiaou.upload.controller;

import com.xiaou.upload.service.impl.LocalFileUploadServiceImpl;
import com.xiaou.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final LocalFileUploadServiceImpl localFileUploadService;

    public UploadController(LocalFileUploadServiceImpl localFileUploadService) {
        this.localFileUploadService = localFileUploadService;
    }

    // 文件上传接口
    @PostMapping("/local")
    public R<String> uploadLocal(@RequestParam("file") MultipartFile file) {
        String url = localFileUploadService.uploadLocal(file);
        return R.ok(url);  // 返回上传后的URL, 该URL也可以用于下载
    }
}
