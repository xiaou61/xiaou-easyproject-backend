package com.xiaou.upload.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.obs.services.ObsClient;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.PutObjectsRequest;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.xiaou.upload.config.AliOssProperties;
import com.xiaou.upload.config.COSProperties;
import com.xiaou.upload.config.ObsProperties;
import com.xiaou.upload.config.OssProperties;
import com.xiaou.upload.service.FileUploadService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private OssProperties ossProperties;

    @Resource
    private COSProperties cosProperties;

    @Resource
    private AliOssProperties aliOssProperties;

    @Resource
    private ObsProperties obsProperties;

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

    @Override
    public String uploadOSS(MultipartFile file) {
        String endpoint = aliOssProperties.getEndpoint();
        String accessKeyId = aliOssProperties.getKeyId();
        String accessKeySecret = aliOssProperties.getKeySecret();
        String bucketName = aliOssProperties.getBucketName();
        String url = null;

        //创建OSSClient实例。
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //获取上传文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取文件名称
        String fileName = file.getOriginalFilename();

        //保证文件名唯一，去掉uuid中的'-'
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + fileName;

        //调用oss方法上传到阿里云
        //第一个参数：Bucket名称
        //第二个参数：上传到oss文件路径和文件名称
        //第三个参数：上传文件输入流
        ossClient.putObject(bucketName, fileName, inputStream);
        //把上传后把文件url返回
        url = "https://" + bucketName + "." + endpoint + "/" + fileName;
        //关闭OSSClient
        ossClient.shutdown();

        return url;

    }

    @Override
    public String uploadOBS(MultipartFile file) {
        String endpoint = obsProperties.getEndpoint();      // OBS终端节点
        String accessKey = obsProperties.getAccessKey();    // Access Key
        String secretKey = obsProperties.getSecretAccessKey();    // Secret Key
        String bucketName = obsProperties.getBucketName();  // Bucket名称

        // 创建OBS客户端
        ObsClient obsClient = new ObsClient(accessKey, secretKey, endpoint);

        try {
            // 获取文件名并加上唯一标识符
            String originalFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String objectKey = uuid + originalFileName;

            // 创建临时文件
            File tempFile = File.createTempFile("obs-temp", null);
            Path tempPath = tempFile.toPath();
            Files.copy(file.getInputStream(), tempPath, StandardCopyOption.REPLACE_EXISTING);
            // 上传
            PutObjectResult putObjectResult = obsClient.putObject(bucketName, objectKey, tempFile);
            log.info(putObjectResult.getObjectUrl());
            return putObjectResult.getObjectUrl();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败：" + e.getMessage());
        } finally {
            try {
                obsClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
