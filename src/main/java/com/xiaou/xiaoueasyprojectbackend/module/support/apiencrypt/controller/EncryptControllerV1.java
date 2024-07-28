package com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.dto.ResponseDTO;
import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.dto.ValidateList;
import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.annotation.ApiDecrypt;
import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.annotation.ApiEncrypt;
import com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.service.ApiEncryptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/7/25 16:15
 * @Description api接口加密解密测试 来自于1024创新实验室开源项目
 * 这里用到了注解的方式 如果需要使用可以直接apiencrypt下的所有文件{@link ApiDecrypt}
 * 这里的JweForm就是加密的内容可以按照自己需求去更改
 * 当前加密算法为：SM2 也可以通过修改这个类来更换{@link ApiEncryptService}
 */
@RestController
@RequestMapping("/v1/apiEncrypt")
@Tag(name = "api接口加密解密测试V1", description = "api接口加密解密测试")
public class EncryptControllerV1 {

    /**
     * 格式要求：{"encryptData":"ZTg0MTYyY2U2YzY0NjBhYjIxMWM0YjFmZTEwOGVkYWZkYTQ0YTA4ODYwMzg3ZWNlMmE0OGZhN2YxNmJjZWZhZg=="}
     * @param form
     * @return
     */
    @ApiDecrypt
    @PostMapping("/apiEncrypt/testRequestEncrypt")
    @Operation(summary = "测试 请求加密")
    public ResponseDTO<JweForm> testRequestEncrypt(@RequestBody @Valid JweForm form) {
        return ResponseDTO.ok(form);
    }

    /**
     *
     * @param form
     * @return
     */
    @ApiEncrypt
    @PostMapping("/apiEncrypt/testResponseEncrypt")
    @Operation(summary = "测试 返回加密")
    public ResponseDTO<JweForm> testResponseEncrypt(@RequestBody @Valid JweForm form) {
        return ResponseDTO.ok(form);
    }

    @ApiDecrypt
    @ApiEncrypt
    @PostMapping("/apiEncrypt/testDecryptAndEncrypt")
    @Operation(summary = "测试 请求参数加密和解密、返回数据加密和解密")
    public ResponseDTO<JweForm> testDecryptAndEncrypt(@RequestBody @Valid JweForm form) {
        return ResponseDTO.ok(form);
    }

    @ApiDecrypt
    @ApiEncrypt
    @PostMapping("/apiEncrypt/testArray")
    @Operation(summary = "测试 数组加密和解密")
    public ResponseDTO<List<JweForm>> testArray(@RequestBody @Valid ValidateList<JweForm> list) {
        return ResponseDTO.ok(list);
    }


    @Data
    public static class JweForm {

        @NotBlank(message = "姓名 不能为空")
        String name;

        @NotNull
        @Min(value = 1)
        Integer age;

    }
}
