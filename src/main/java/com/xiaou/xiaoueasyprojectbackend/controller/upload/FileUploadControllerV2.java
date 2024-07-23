package com.xiaou.xiaoueasyprojectbackend.controller.upload;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.xiaou.xiaoueasyprojectbackend.common.core.domain.enums.ErrorCode;
import com.xiaou.xiaoueasyprojectbackend.common.domain.model.dto.ResponseDTO;
import com.xiaou.xiaoueasyprojectbackend.common.domain.model.dto.file.UploadDTO;
import com.xiaou.xiaoueasyprojectbackend.common.exception.BusinessException;
import com.xiaou.xiaoueasyprojectbackend.common.utils.FileUploadUtils;
import com.xiaou.xiaoueasyprojectbackend.common.utils.ServletHolderUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/23 16:31
 * @Description: 文件上传 V2版本 参考了agileboot
 * 这里要修改 {@link FileUploadUtils#getFileAbsolutePath 里面的绝对路径}
 * 这里也需要修改映射 {@link com.xiaou.xiaoueasyprojectbackend.common.config.WebConfig#addResourceHandlers(ResourceHandlerRegistry)}
 * 这里需要的注意的是 这个不能和Xfile共存 所以说本项目里测试不出来效果 只有你单独的抽离开后才能测试出来效果。
 */
@RestController
@RequestMapping("/v2/upload")
public class FileUploadControllerV2 {
    /**
     * 通用上传请求（单个）
     */
    @Operation(summary = "单个上传文件")
    @PostMapping("/upload")
    public ResponseDTO<UploadDTO> uploadFile(MultipartFile file) {
        if (file == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload("upload", file);

        String url = ServletHolderUtil.getContextUrl() + fileName;

        UploadDTO uploadDTO = UploadDTO.builder()
                // 全路径
                .url(url)
                // 相对路径
                .fileName(fileName)
                // 新生成的文件名
                .newFileName(FileNameUtil.getName(fileName))
                // 原始的文件名
                .originalFilename(file.getOriginalFilename()).build();

        return ResponseDTO.ok(uploadDTO);
    }

    /**
     * 通用上传请求（多个）
     */
    @Operation(summary = "多个上传文件")
    @PostMapping("/uploads")
    public ResponseDTO<List<UploadDTO>> uploadFiles(List<MultipartFile> files) {
        if (CollUtil.isEmpty(files)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }

        List<UploadDTO> uploads = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload("upload", file);
                String url = ServletHolderUtil.getContextUrl() + fileName;
                UploadDTO uploadDTO = UploadDTO.builder()
                        .url(url)
                        .fileName(fileName)
                        .newFileName(FileNameUtil.getName(fileName))
                        .originalFilename(file.getOriginalFilename()).build();

                uploads.add(uploadDTO);

            }
        }
        return ResponseDTO.ok(uploads);
    }
}
