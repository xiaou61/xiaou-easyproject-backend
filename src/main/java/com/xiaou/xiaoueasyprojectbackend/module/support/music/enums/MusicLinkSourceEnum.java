package com.xiaou.xiaoueasyprojectbackend.module.support.music.enums;

import com.xiaou.xiaoueasyprojectbackend.module.support.music.util.DataTool;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum MusicLinkSourceEnum {
    /**
     * 0-github
     */
    GITHUB(0, "github"),
    /**
     * 1-alist
     */
    ALIST(1, "alist"),
    ;

    private Integer code;
    private String desc;

    private static Map<Integer, MusicLinkSourceEnum> map = DataTool.enumMap(MusicLinkSourceEnum.class, MusicLinkSourceEnum::getCode);

    public static MusicLinkSourceEnum ofCode(Integer code) {
        return map.get(code);
    }

    public boolean match(Integer code) {
        return Objects.equals(code, this.code);
    }

    public static String getDescByCode(Integer code) {
        return map.get(code).getDesc();
    }

}
