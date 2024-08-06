package com.xiaou.xiaoueasyprojectbackend.module.support.music.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author woodwhales on 2023-03-28 16:26
 */
@Data
public class MusicDetailRequestBody {

    @NotNull(message = "id不允许为空")
    private Long id;

}
