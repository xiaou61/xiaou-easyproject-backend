package com.xiaou.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.music.model.domain.Singer;
import com.xiaou.music.model.req.SingerRequest;
import com.xiaou.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface SingerService extends IService<Singer> {
    R addSinger(SingerRequest addSingerRequest);

    R deleteSinger(Integer id);

    R allSinger();

    R<PageRespDto<Singer>> allSingerPage(PageReqDto pageReqDto);

    R singerOfName(String name);

    R singerOfSex(Integer sex);

    R updateSingerMsg(SingerRequest updateSingerRequest);

    R updateSingerPic(MultipartFile avatorFile, int id);


}
