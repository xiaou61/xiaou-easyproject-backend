package com.xiaou.xiaoueasyprojectbackend.module.support.music.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @projectName: woodwhales-music
 * @author: woodwhales
 * @date: 20.8.15 22:33
 * @description:
 */
@Data
public class ParseMusicRequestBody {

    @NotNull(message = "要解析的平台不允许为空")
    private String platformType;

    @NotBlank(message = "要解析的内容不允许为空")
    private String content;

}
