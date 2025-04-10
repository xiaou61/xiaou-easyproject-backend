package org.dromara.dynamicqr.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.dromara.dynamicqr.enums.QrCodeType;

import java.time.LocalDateTime;

@Data
@TableName("dynamic_qr")
public class DynamicQr {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private QrCodeType type;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
