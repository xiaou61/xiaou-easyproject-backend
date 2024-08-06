package com.xiaou.xiaoueasyprojectbackend.module.support.music.enums;

import com.xiaou.xiaoueasyprojectbackend.module.support.music.entity.MusicInfoLink;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.util.DataTool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum MusicLinkTypeEnum {
    /**
     * 0-音频
     */
    AUDIO_LINK(0, "音频"),
    /**
     * 1-封面
     */
    COVER_LINK(1, "封面"),
    ;

    private Integer code;
    private String desc;

    private static Map<Integer, MusicLinkTypeEnum> map = DataTool.enumMap(MusicLinkTypeEnum.class, MusicLinkTypeEnum::getCode);
    private static Map<String, MusicLinkTypeEnum> nameMap = DataTool.enumMap(MusicLinkTypeEnum.class, MusicLinkTypeEnum::name);

    public boolean match(Integer code) {
        return Objects.equals(code, this.code);
    }

    public static MusicLinkTypeEnum ofCode(Integer code) {
        return map.get(code);
    }

    public static MusicLinkTypeEnum getMusicLinkTypeEnum(String linkName) {
        return nameMap.get(linkName);
    }

    public static Map<String, String> buildLinkMap(List<MusicInfoLink> musicInfoLinkList) {
        Map<String, String> result = new HashMap<>();
        for (MusicLinkTypeEnum linkTypeEnum : MusicLinkTypeEnum.values()) {
            List<MusicInfoLink> linkList = DataTool.filter(musicInfoLinkList, link -> linkTypeEnum.match(link.getLinkType()));
            if(CollectionUtils.isNotEmpty(linkList)) {
                result.put(linkTypeEnum.name(), linkList.get(0).getLinkUrl());
            } else {
                result.put(linkTypeEnum.name(), StringUtils.EMPTY);
            }
        }
        return result;
    }

}
