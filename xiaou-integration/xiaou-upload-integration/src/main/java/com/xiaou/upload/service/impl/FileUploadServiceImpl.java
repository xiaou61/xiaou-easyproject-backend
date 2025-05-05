package com.xiaou.upload.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.xiaou.upload.config.COSProperties;
import com.xiaou.upload.config.OssProperties;
import com.xiaou.upload.service.FileUploadService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private OssProperties ossProperties;

    @Resource
    private COSProperties cosProperties;

    @Value("${server.servlet.context-path}")
    private String contextPath;  // 自动获取context-path


    @Override
    public String uploadLocal(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new RuntimeException("上传文件的文件名为空");
        }

        String ext = FileUtil.extName(originalFilename);
        String fileName = IdUtil.fastSimpleUUID() + "." + ext;

        // 确保路径以斜杠结尾，避免路径拼接错误
        String localPath = ossProperties.getLocalPath();
        if (!localPath.endsWith(File.separator)) {
            localPath += File.separator;
        }

        // 构建完整的文件路径
        String fullPath = localPath + fileName;
        File dest = new File(fullPath);

        try {
            // 创建父目录
            FileUtil.mkParentDirs(dest);

            // 转存文件
            file.transferTo(dest);

            // 自动加上 context-path 前缀
            String url = ossProperties.getServiceHost() + contextPath + ossProperties.getAccessPrefix() + "/" + fileName;

            log.info("上传成功，访问地址：{}", url);
            return url;

        } catch (IOException e) {
            log.error("本地上传失败", e);
            throw new RuntimeException("上传失败", e);
        }
    }

    @Override
    public String uploadCOS(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new RuntimeException("上传文件的文件名为空");
        }

        String ext = FileUtil.extName(originalFilename);
        String fileName = IdUtil.fastSimpleUUID() + "." + ext;

        // 初始化 COS 客户端
        COSCredentials cred = new BasicCOSCredentials(cosProperties.getSecretId(), cosProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosProperties.getRegion()));
        COSClient cosClient = new COSClient(cred, clientConfig);

        try (InputStream inputStream = file.getInputStream()) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosProperties.getBucketName(), fileName, inputStream, null);
            TransferManager transferManager = new TransferManager(cosClient);
            Upload upload = transferManager.upload(putObjectRequest);
            upload.waitForCompletion();

            return cosProperties.getBaseUrl() + "/" + fileName;
        } catch (Exception e) {
            log.error("COS 上传失败", e);
            throw new RuntimeException("COS 上传失败", e);
        } finally {
            cosClient.shutdown();
        }
    }
}
