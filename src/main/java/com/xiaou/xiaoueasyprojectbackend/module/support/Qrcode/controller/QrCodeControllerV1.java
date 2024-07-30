package com.xiaou.xiaoueasyprojectbackend.module.support.Qrcode.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.Qrcode.generator.QRCodeGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou61
 * @Date 2024/7/30 13:52
 * @Description: Ercode生成 zxing
 */
@RestController
@RequestMapping("/v1/qrcode")
@Tag(name = "Ercode生成 zxing")
public class QrCodeControllerV1 {
    @GetMapping("/gen")
    public void gen(@RequestParam("url") String url, HttpServletResponse response){
        QRCodeGenerator.generateQRCode(url, response);
    }
}
