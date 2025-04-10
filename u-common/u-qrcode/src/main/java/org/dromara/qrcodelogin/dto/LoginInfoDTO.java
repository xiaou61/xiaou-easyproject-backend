package org.dromara.qrcodelogin.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 对应数据库表：u_qrlogin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 设备号
     */
    private String device;

    /**
     * JWT令牌
     */
    private String token;

    /**
     * 扫码状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
