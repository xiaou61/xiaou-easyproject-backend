package com.xiaou.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.minio.utils.MinIOUtils;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.music.mapper.SingerMapper;
import com.xiaou.music.model.domain.Singer;
import com.xiaou.music.model.req.SingerRequest;
import com.xiaou.music.service.SingerService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Resource
    private SingerMapper singerMapper;

    @Override
    public R addSinger(SingerRequest addSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(addSingerRequest, singer);
        String pic = "/img/avatorImages/user.jpg";
        singer.setPic(pic);
        if (singerMapper.insert(singer) > 0) {
            return R.ok("添加成功");
        } else {
            return R.fail("添加失败");
        }
    }

    @Override
    public R deleteSinger(Integer id) {
        if (singerMapper.deleteById(id) > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @Override
    public R allSinger() {
        return R.ok(singerMapper.selectList(null));
    }

    @Override
    public R<PageRespDto<Singer>> allSingerPage(PageReqDto dto) {
        IPage<Singer> page = new Page<>();
        page.setCurrent(dto.getPageNum());
        page.setSize(dto.getPageSize());
        IPage<Singer> singerInfoPage = singerMapper.selectPage(page, new QueryWrapper<>());
        return R.ok(PageRespDto.of(dto.getPageNum(), dto.getPageSize(), page.getTotal(),
                singerInfoPage.getRecords().stream().toList()));

    }

    @Override
    public R singerOfName(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return R.ok(singerMapper.selectList(queryWrapper));
    }

    @Override
    public R singerOfSex(Integer sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sex", sex);
        return R.ok(singerMapper.selectList(queryWrapper));
    }

    @Override
    public R updateSingerMsg(SingerRequest updateSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(updateSingerRequest, singer);
        if (singerMapper.updateById(singer) > 0) {
            return R.ok("修改成功");
        } else {
            return R.fail("修改失败");
        }
    }

    @Override
    public R updateSingerPic(MultipartFile avatorFile, int id) {
        String fileName = avatorFile.getOriginalFilename();
        try {
            MinIOUtils.uploadFile("test1", avatorFile, fileName, "image/jpeg");
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new RuntimeException("上传失败");
        }
        String imgPath = "/test1/singer/img/" + fileName;
        Singer singer = new Singer();
        singer.setId(id);
        singer.setPic(imgPath);
        if (singerMapper.updateById(singer) > 0) {
            return R.ok("上传成功", imgPath);
        } else {
            return R.fail("上传失败");
        }
    }
}
