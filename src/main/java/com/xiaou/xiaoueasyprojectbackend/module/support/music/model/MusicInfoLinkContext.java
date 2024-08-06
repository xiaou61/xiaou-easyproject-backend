package com.xiaou.xiaoueasyprojectbackend.module.support.music.model;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.config.AppConfig;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.entity.MusicInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.entity.MusicInfoLink;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.enums.MusicLinkTypeEnum;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.service.impl.MusicLinkServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.util.DataTool;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class MusicInfoLinkContext {
    private List<MusicInfo> musicInfoList;
    private Map<Long, MusicInfoLink> audioUrlMapping;
    private Map<Long, MusicInfoLink> coverUrlMapping;

    public String getCoverUrl(Long musicId) {
        if(this.coverUrlMapping.containsKey(musicId)) {
            return this.coverUrlMapping.get(musicId).getLinkUrl();
        } else {
            return null;
        }
    }

    public String getAudioUrl(Long musicId) {
        if(this.audioUrlMapping.containsKey(musicId)) {
            return this.audioUrlMapping.get(musicId).getLinkUrl();
        } else {
            return null;
        }
    }

    public MusicInfoLinkContext(List<MusicInfo> musicInfoList) {
        this.musicInfoList = musicInfoList;
        if(CollectionUtils.isNotEmpty(this.musicInfoList)) {
            AppConfig appConfig = SpringUtil.getBean(AppConfig.class);
            MusicLinkServiceImpl musicLinkService = SpringUtil.getBean(MusicLinkServiceImpl.class);
            List<MusicInfoLink> musicInfoLinkList = musicLinkService.getLinkInfoListByMusicIds(DataTool.toList(this.musicInfoList, MusicInfo::getId));
            this.audioUrlMapping = DataTool.toMap(DataTool.filter(musicInfoLinkList,
                            musicLink -> MusicLinkTypeEnum.AUDIO_LINK.match(musicLink.getLinkType())
                                    && appConfig.getMusicLinkSourceEnum().match(musicLink.getLinkSource())),
                    MusicInfoLink::getMusicId);
            this.coverUrlMapping = DataTool.toMap(DataTool.filter(musicInfoLinkList,
                            musicLink -> MusicLinkTypeEnum.COVER_LINK.match(musicLink.getLinkType())
                                    && appConfig.getMusicLinkSourceEnum().match(musicLink.getLinkSource())),
                    MusicInfoLink::getMusicId);
        } else {
            this.audioUrlMapping = Collections.emptyMap();
            this.coverUrlMapping = Collections.emptyMap();
        }
    }
}
