package com.xiaou.upload.controller;

import com.xiaou.upload.service.FileUploadService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private FileUploadService fileUploadService;


    // 文件上传接口
    @PostMapping("/local")
    public R<String> uploadLocal(@RequestParam("file") MultipartFile file) {
        String url = fileUploadService.uploadLocal(file);
        return R.ok(url);  // 返回上传后的URL, 该URL也可以用于下载
    }

    // 批量文件上传接口本地
    @PostMapping("/local/batch")
    public R<List<String>> uploadLocalBatch(@RequestParam("files") MultipartFile[] files) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String url = fileUploadService.uploadLocal(file);
                urls.add(url);
            }
        }

        return R.ok(urls); // 返回所有上传后的URL列表
    }


    @PostMapping("/cos")
    public R<String> uploadFile(@RequestParam("file") MultipartFile file) {

        // 判断文件是否为空
        if (file.getSize() == 0) {
            return R.fail("文件为空");
        }
        String url = fileUploadService.uploadCOS(file);
        return R.ok(url);

    }

    @PostMapping("/oss")
    public R<String> uploadFileOss(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (file.getSize() == 0) {
            return R.fail("文件为空");
        }
        String url = fileUploadService.uploadOSS(file);
        return R.ok(url);
    }

}
