package com.xiaou.xiaoueasyprojectbackend.module.support.upload.controller;


import com.xiaou.xiaoueasyprojectbackend.common.utils.AliOssUtil;
import com.xiaou.xiaoueasyprojectbackend.config.WebConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author xiaou61
 * @Date 2024/7/18 13:25
 * @Description: 图片上传 建议直接看FileUploadController 这个因为很file有冲突 有一些未知bug
 * 如果你只是需求图片上传可以看一下这个 改方法已废弃。
 */
@Deprecated
@RestController
@RequestMapping("/v1/upload/pic")
@Tag(name = "图片上传V1")
public class PicUploadControllerV1 {
    private static final Logger log = LoggerFactory.getLogger(PicUploadControllerV1.class);

    @Value("${upload.path}")
    private String uploadPath;

    /**
     * @Author xiaou61
     * @Date 2024/7/18 下午1:16
     * @Description 图片上传-本地实现 需要配置webConfig 具体见 {@link WebConfig#addResourceHandlers}
     * @Since version 1.0
     */
    @Operation(description = "图片上传-本地实现")
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        File dest = new File(uploadPath + imageName);
        image.transferTo(dest);
        log.info("图片后端地址 " + "/api/images" + imageName);
        return "/api/images/" + imageName;
    }

    /**
     * @Author xiaou61
     * @Date 2024/7/18 下午1:19
     * @Description 图片上传-本地实现-根据图片名字获得地址
     * @Since version 1.0
     */
    @Operation(description = "图片上传-本地实现-根据图片名字获得地址")
    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
        File file = new File(uploadPath + imageName);
        Resource resource = new UrlResource(file.toURI());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    /**
     * @Author xiaou61
     * @Date 2024/7/18 下午1:20
     * @Description 图片上传-oss实现
     * @Since version 1.0
     */
    @Operation(description = "图片上传-oss实现")
    @PostMapping("/oss/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 调用阿里云 OSS 工具类上传文件
            String objectName = file.getOriginalFilename();
            String url = AliOssUtil.uploadFile(objectName, file.getInputStream());
            return "File uploaded successfully! URL: " + url;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }



}
