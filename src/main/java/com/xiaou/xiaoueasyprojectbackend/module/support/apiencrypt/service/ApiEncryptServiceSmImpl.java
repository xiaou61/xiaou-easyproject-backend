package com.xiaou.xiaoueasyprojectbackend.module.support.apiencrypt.service;

import cn.hutool.crypto.symmetric.SM4;
import lombok.extern.slf4j.Slf4j;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Base64;

 /**
 * 国产 SM4 加密 和 解密
 *
 */

@Slf4j
@Service
public class ApiEncryptServiceSmImpl implements ApiEncryptService {

    private static final String CHARSET = "UTF-8";
    private static final String SM4_KEY = "1024abcd1024abcd1024abcd1024abcd";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    @Override
    public String encrypt(String data) {
        try {

            // 第一步： SM4 加密
            SM4 sm4 = new SM4(hexToBytes(SM4_KEY));
            String encryptHex = sm4.encryptHex(data);

            // 第二步： Base64 编码
            return new String(Base64.getEncoder().encode(encryptHex.getBytes(CHARSET)), CHARSET);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "EMPTY";
        }
    }


    @Override
    public String decrypt(String data) {
        try {

            // 第一步： Base64 解码
            byte[] base64Decode = Base64.getDecoder().decode(data);

            // 第二步： SM4 解密
            SM4 sm4 = new SM4(hexToBytes(SM4_KEY));
            return sm4.decryptStr(new String(base64Decode));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "EMPTY";
        }
    }


    /**
     * 16 进制串转字节数组
     *
     * @param hex 16进制字符串
     * @return byte数组
     */
    public static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] result;
        if (length % 2 == 1) {
            length++;
            result = new byte[(length / 2)];
            hex = "0" + hex;
        } else {
            result = new byte[(length / 2)];
        }
        int j = 0;
        for (int i = 0; i < length; i += 2) {
            result[j] = hexToByte(hex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * 16 进制字符转字节
     *
     * @param hex 16进制字符 0x00到0xFF
     * @return byte
     */
    private static byte hexToByte(String hex) {
        return (byte) Integer.parseInt(hex, 16);
    }

}
