package com.xiaou.xiaoueasyprojectbackend.module.support.music.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicInfoVo {

    private String title;

    private String artist;
    
    private String album;

    private String coverUrl;

    private String audioUrl;
    
    private String ogg;

    public MusicInfoVo(String title, String artist, String album, String audioUrl, String coverUrl) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.coverUrl = coverUrl;
        this.audioUrl = audioUrl;
    }

}
