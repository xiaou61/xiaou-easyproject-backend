package com.xiaou.xiaoueasyprojectbackend.module.support.Qrcode.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.Qrcode.generator.QRCodeGenerator2;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaou61
 * @Date 2024/7/30 13:54
 * @Description: Github开源项目qrcode-plugin
 */
@RestController
@RequestMapping("/v2/qrcode")
@Tag(name = "二维码生成接口V2", description = "二维码生成接口V2")
public class QrCodeControllerV2 {
    @GetMapping("/black-and-white")
    public void generateBlackAndWhiteQRCode(@RequestParam String url, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        QRCodeGenerator2.generateBlackAndWhiteQRCode(url, response);
    }

    @PostMapping("/with-logo")
    public void generateQRCodeWithLogo(@RequestParam String url,
                                       @RequestParam("logo") MultipartFile logoFile,
                                       HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        try (InputStream logoStream = logoFile.getInputStream()) {
            QRCodeGenerator2.generateQRCodeWithLogo(url, response, logoStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read logo file", e);
        }
    }

    @GetMapping("/color")
    public void generateColorQRCode(@RequestParam String url, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        QRCodeGenerator2.generateColorQRCode(url, response);
    }

    @PostMapping("/with-background")
    public void generateBackgroundImageQRCode(@RequestParam String url,
                                              @RequestParam("background") MultipartFile backgroundFile,
                                              HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        try (InputStream bgImgStream = backgroundFile.getInputStream()) {
            QRCodeGenerator2.generateBackgroundImageQRCode(url, response, bgImgStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read background image file", e);
        }
    }

    @GetMapping("/special-shape")
    public void generateSpecialShapeQRCode(@RequestParam String url, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        QRCodeGenerator2.generateSpecialShapeQRCode(url, response);
    }

    @PostMapping("/image-filled")
    public void generateImageFilledQRCode(@RequestParam String url,
                                          @RequestParam("image") MultipartFile imgFile,
                                          HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        try (InputStream imgStream = imgFile.getInputStream()) {
            QRCodeGenerator2.generateImageFilledQRCode(url, response, imgStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }

    @PostMapping("/gif")
    public void generateGifQRCode(@RequestParam String url,
                                  @RequestParam("background") MultipartFile backgroundFile,
                                  HttpServletResponse response) throws Exception {
        response.setContentType("image/gif");
        try (InputStream bgImgStream = backgroundFile.getInputStream()) {
            QRCodeGenerator2.generateGifQRCode(url, response, bgImgStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read background image file", e);
        }
    }

}
