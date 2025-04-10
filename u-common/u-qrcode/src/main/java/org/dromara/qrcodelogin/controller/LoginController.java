package org.dromara.qrcodelogin.controller;

import com.alibaba.fastjson.JSONObject;

import org.dromara.qrcodelogin.dto.LoginInfoDTO;
import org.dromara.qrcodelogin.enums.LoginStatusEnum;
import org.dromara.qrcodelogin.utils.JwtUtil;
import org.dromara.qrcodelogin.utils.QRCodeUtil;
import org.dromara.qrcodelogin.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author xiaou61
 * @Date 2025/4/7 下午6:25
 * @Description 二维码登录qrcode
 * @Since version 1.0
 */
@Slf4j
@RestController
@Tag(name = "二维码登录qrcode")
@RequestMapping("/uapi/qrcode")
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String cacheKey(String uuid) {
        return "users:login:" + uuid;
    }

    private void cache(LoginInfoDTO loginInfoDTO) {
        stringRedisTemplate.opsForValue().set(
            cacheKey(loginInfoDTO.getUuid()),
            JSONObject.toJSONString(loginInfoDTO),
            2,
            TimeUnit.MINUTES
        );
    }

    private LoginInfoDTO getCache(String uuid) {
        String s = stringRedisTemplate.opsForValue().get(cacheKey(uuid));
        return s == null ? null : JSONObject.parseObject(s, LoginInfoDTO.class);
    }

    /**
     * 生成二维码
     */
    @GetMapping("/generate")
    @Operation(summary = "生成二维码")
    public ResponseEntity<ResponseVO> generateQRCode() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String base64QR = QRCodeUtil.generateQRCode(uuid, 200, 200);

        LocalDateTime now = LocalDateTime.now();
        LoginInfoDTO loginInfoDTO = LoginInfoDTO.builder()
            .uuid(uuid)
            .status(LoginStatusEnum.UNSCANNED.name())
            .createTime(now)
            .updateTime(now)
            .build();

        cache(loginInfoDTO);

        ResponseVO vo = ResponseVO.builder()
            .uuid(uuid)
            .qrcode("data:image/png;base64," + base64QR)
            .build();

        log.info("✅ 生成二维码成功 uuid: {}", uuid);
        return ResponseEntity.ok(vo);
    }

    /**
     * 检查扫码状态
     */
    @GetMapping("/check/{uuid}")
    @Operation(summary = "检查扫码状态")
    public ResponseEntity<?> checkStatus(@PathVariable String uuid) {
        LoginInfoDTO loginInfoDTO = getCache(uuid);
        if (loginInfoDTO == null) {
            return ResponseEntity.status(410).body("二维码已过期");
        }

        String token = "";
        if (LoginStatusEnum.CONFIRMED.name().equals(loginInfoDTO.getStatus())) {
            token = JwtUtil.generateAuthToken(uuid);
            loginInfoDTO.setToken(token);
            loginInfoDTO.setUpdateTime(LocalDateTime.now());
            cache(loginInfoDTO);
        }

        ResponseVO vo = ResponseVO.builder()
            .token(token)
            .status(loginInfoDTO.getStatus())
            .build();

        log.info("🧐 校验二维码状态 uuid: {}, 状态: {}", uuid, loginInfoDTO.getStatus());
        return ResponseEntity.ok(vo);
    }

    /**
     * 扫码（手机端）
     */
    @PostMapping("/scan/{uuid}")
    @Operation(summary = "扫码（手机端）")
    public ResponseEntity<?> scanQrCode(@PathVariable String uuid) {
        LoginInfoDTO loginInfoDTO = getCache(uuid);
        if (loginInfoDTO == null) {
            return ResponseEntity.status(410).body("二维码已过期");
        }

        loginInfoDTO.setStatus(LoginStatusEnum.SCANNED.name());
        loginInfoDTO.setUpdateTime(LocalDateTime.now());
        cache(loginInfoDTO);

        log.info("📱 扫码成功 uuid: {}", uuid);
        return ResponseEntity.ok().build();
    }

    /**
     * 确认登录（手机端）
     */
    @PostMapping("/confirm/{uuid}")
    @Operation(summary = "确认登录（手机端）")
    public ResponseEntity<?> confirm(@PathVariable String uuid) {
        LoginInfoDTO loginInfoDTO = getCache(uuid);
        if (loginInfoDTO == null) {
            return ResponseEntity.status(410).body("二维码已过期");
        }

        loginInfoDTO.setStatus(LoginStatusEnum.CONFIRMED.name());
        loginInfoDTO.setUpdateTime(LocalDateTime.now());
        cache(loginInfoDTO);

        log.info("🔒 确认登录成功 uuid: {}", uuid);
        return ResponseEntity.ok().build();
    }
}
