package com.xiaou.music.controller;

import com.xiaou.music.model.req.SongRequest;
import com.xiaou.music.service.SongService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SongController {

    @Resource
    private SongService songService;

    /**
     * 添加歌曲
     * @param addSongRequest
     * @param lrcfile 歌词
     * @param mpfile 歌曲
     * @return
     */
    @PostMapping("/song/add")
    public R addSong(SongRequest addSongRequest, @RequestParam("lrcfile") MultipartFile lrcfile, @RequestParam("file") MultipartFile mpfile) {
        return songService.addSong(addSongRequest,lrcfile,mpfile);
    }

    // 删除歌曲
    @DeleteMapping("/song/delete")
    public R deleteSong(@RequestParam int id) {
        return songService.deleteSong(id);
    }

    // 返回所有歌曲
    @GetMapping("/song")
    public R allSong() {
        return songService.allSong();
    }

    // 返回指定歌曲ID的歌曲
    @GetMapping("/song/detail")
    public R songOfId(@RequestParam int id) {
        return songService.songOfId(id);
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("/song/singer/detail")
    public R songOfSingerId(@RequestParam int singerId) {
        return songService.songOfSingerId(singerId);
    }


    // 返回指定歌手名的歌曲
    @GetMapping("/song/singerName/detail")
    public R songOfSingerName(@RequestParam String name) {
        return songService.songOfSingerName('%' + name + '%');
    }

    // 更新歌曲信息
    @PostMapping("/song/update")
    public R updateSongMsg(@RequestBody SongRequest updateSongRequest) {
        return songService.updateSongMsg(updateSongRequest);
    }

    // 更新歌曲图片
    @PostMapping("/song/img/update")
    public R updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongPic(urlFile, id);
    }

    // 更新歌曲
    @PostMapping("/song/url/update")
    public R updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        return songService.updateSongUrl(urlFile, id);
    }
    ///song/lrc/update
    //更新歌词
    @PostMapping("/song/lrc/update")
    public R updateSongLrc(@RequestParam("file") MultipartFile lrcFile, @RequestParam("id") int id) {
        return songService.updateSongLrc(lrcFile, id);
    }

}
