package com.xiaou.music.controller;


import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.music.model.domain.Singer;
import com.xiaou.music.model.req.SingerRequest;
import com.xiaou.music.service.SingerService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SingerController {

    @Resource
    private SingerService singerService;

    @PostMapping("/singer/add")
    public R addSinger(@RequestBody SingerRequest addSingerRequest) {
        return singerService.addSinger(addSingerRequest);
    }


    // 删除歌手
    @DeleteMapping("/singer/delete/{id}")
    public R deleteSinger(@PathVariable("id") Integer id) {
        return singerService.deleteSinger(id);
    }

    // 返回所有歌手 不分页
    @GetMapping("/singer")
    public R allSinger() {
        return singerService.allSinger();
    }

    // 返回所有歌手 分页
    @PostMapping("/singer/page")
    public R<PageRespDto<Singer>> allSingerPage(@RequestBody PageReqDto pageReqDto) {
        return singerService.allSingerPage(pageReqDto);
    }

    // 根据歌手名查找歌手
    @GetMapping("/singer/name/detail/{name}")
    public R singerOfName(@PathVariable String name) {
        return singerService.singerOfName(name);
    }

    // 根据歌手性别查找歌手
    @GetMapping("/singer/sex/detail/{sex}")
    public R singerOfSex(@PathVariable int sex) {
        return singerService.singerOfSex(sex);
    }

    // 更新歌手信息
    @PostMapping("/singer/update")
    public R updateSingerMsg(@RequestBody SingerRequest updateSingerRequest) {
        return singerService.updateSingerMsg(updateSingerRequest);
    }

    // 更新歌手头像
    @PostMapping("/singer/avatar/update")
    public R updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return singerService.updateSingerPic(avatorFile, id);
    }

}
