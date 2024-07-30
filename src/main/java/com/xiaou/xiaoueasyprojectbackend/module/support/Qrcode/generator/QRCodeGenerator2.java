package com.xiaou.xiaoueasyprojectbackend.module.support.Qrcode.generator;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class QRCodeGenerator2 {

    // 生成黑白二维码
    public static void generateBlackAndWhiteQRCode(String url, HttpServletResponse response) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url).asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 生成带有logo的二维码
    public static void generateQRCodeWithLogo(String url, HttpServletResponse response, InputStream logoStream) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setLogo(logoStream)
                .setLogoRate(7)
                .setLogoStyle(QrCodeOptions.LogoStyle.ROUND)
                .asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 生成彩色二维码
    public static void generateColorQRCode(String url, HttpServletResponse response) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setDrawPreColor(Color.BLUE)
                .asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 生成背景图二维码
    public static void generateBackgroundImageQRCode(String url, HttpServletResponse response, InputStream bgImgStream) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setBgImg(bgImgStream)
                .setBgOpacity(0.7F)
                .asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 特殊形状二维码
    public static void generateSpecialShapeQRCode(String url, HttpServletResponse response) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setDrawEnableScale(true)
                .setDrawStyle(QrCodeOptions.DrawStyle.DIAMOND)
                .asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 图片填充二维码
    public static void generateImageFilledQRCode(String url, HttpServletResponse response, InputStream imgStream) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1, 1, imgStream)
                .asBufferedImage();
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 生成gif动图二维码
    public static void generateGifQRCode(String url, HttpServletResponse response, InputStream bgImgStream) throws Exception {
        BufferedImage image = QrCodeGenWrapper.of(url)
                .setW(500)
                .setH(500)
                .setBgImg(bgImgStream)
                .setBgOpacity(0.6f)
                .setPicType("gif")
                .asBufferedImage();
        ImageIO.write(image, "gif", response.getOutputStream());
    }
}
