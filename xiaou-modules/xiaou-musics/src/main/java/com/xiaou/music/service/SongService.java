package com.xiaou.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.music.model.domain.Song;
import com.xiaou.music.model.req.SongRequest;
import com.xiaou.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface SongService extends IService<Song> {
    R addSong (SongRequest addSongRequest, MultipartFile lrcfile, MultipartFile mpfile);

    R updateSongMsg(SongRequest updateSongRequest);

    R updateSongUrl(MultipartFile urlFile, int id);

    R updateSongPic(MultipartFile urlFile, int id);

    R deleteSong(Integer id);

    R allSong();

    R songOfSingerId(Integer singerId);

    R songOfId(Integer id);

    R songOfSingerName(String name);

    R updateSongLrc(MultipartFile lrcFile, int id);
}
