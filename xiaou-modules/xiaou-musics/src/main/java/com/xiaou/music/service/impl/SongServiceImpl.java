package com.xiaou.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.minio.utils.MinIOUtils;
import com.xiaou.music.mapper.SongMapper;
import com.xiaou.music.model.domain.Song;
import com.xiaou.music.model.req.SongRequest;
import com.xiaou.music.service.SongService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {
    @Resource
    private SongMapper songMapper;


    @Value("${minio.bucketName}")
    private String bucketName;


    @Override
    public R addSong(SongRequest addSongRequest, MultipartFile lrcfile, MultipartFile mpfile) {
        Song song = new Song();
        BeanUtils.copyProperties(addSongRequest, song);
        String pic = "/img/songPic/tubiao.jpg";
        String fileName = mpfile.getOriginalFilename();
        try {
            MinIOUtils.uploadFile(bucketName, mpfile, fileName, "audio/mpeg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String storeUrlPath = "/" + bucketName + "/" + fileName;
        song.setCreateTime(new Date());
        song.setUpdateTime(new Date());
        song.setPic(pic);
        song.setUrl(storeUrlPath);

        if (lrcfile != null) {
            byte[] fileContent;
            try {
                fileContent = lrcfile.getBytes();
                String content = new String(fileContent, StandardCharsets.UTF_8);
                // 直接清理控制字符（包括 BOM）
                content = StringUtils.strip(content, "\uFEFF");
                song.setLyric(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (songMapper.insert(song) > 0) {
            return R.ok("上传成功", storeUrlPath);
        } else {
            return R.fail("上传失败");
        }
    }

    @Override
    public R updateSongMsg(SongRequest updateSongRequest) {
        Song song = new Song();
        BeanUtils.copyProperties(updateSongRequest, song);
        if (songMapper.updateById(song) > 0) {
            return R.ok("修改成功");
        } else {
            return R.fail("修改失败");
        }
    }

    @Override
    public R updateSongUrl(MultipartFile urlFile, int id) {
        Song song = songMapper.selectById(id);
        if (song == null) {
            return R.fail("歌曲不存在");
        }
        // 删除旧文件
        String oldFileName = Paths.get(song.getUrl()).getFileName().toString();
        try {
            MinIOUtils.removeFile(bucketName, oldFileName);
        } catch (Exception e) {
            throw new RuntimeException("删除旧文件失败：" + e.getMessage(), e);
        }
        // 上传新文件
        String newFileName = urlFile.getOriginalFilename();
        try {
            MinIOUtils.uploadFile(bucketName, urlFile, newFileName, "audio/mpeg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 更新数据库
        String newUrl = "/" + bucketName + "/" + newFileName;
        song.setUrl(newUrl);
        song.setName(newFileName);
        song.setUpdateTime(new Date()); // 建议也更新一下更新时间

        if (songMapper.updateById(song) > 0) {
            return R.ok("更新成功", newUrl);
        } else {
            return R.fail("数据库更新失败");
        }
    }


    @Override
    public R updateSongPic(MultipartFile urlFile, int id) {
        String fileName = urlFile.getOriginalFilename();
        String storeUrlPath = "/user01/singer/song/" + fileName;
        try {
            MinIOUtils.uploadFile(bucketName, urlFile, fileName, "image/jpeg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Song song = new Song();
        song.setId(id);
        song.setPic(storeUrlPath);
        if (songMapper.updateById(song) > 0) {
            return R.ok("上传成功", storeUrlPath);
        } else {
            return R.fail("上传失败");
        }
    }

    @Override
    public R deleteSong(Integer id) {
        Song song = songMapper.selectById(id);
        if (song == null) {
            return R.fail("歌曲不存在");
        }

        //从完整的URL路径中提取出文件名
        String fileName = Paths.get(song.getUrl()).getFileName().toString();

        try {
            if (songMapper.deleteById(id) > 0) {
                MinIOUtils.removeFile(bucketName, fileName);
                return R.ok("删除成功");
            } else {
                return R.fail("删除失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败: " + e.getMessage(), e);
        }
    }


    @Override
    public R allSong() {
        return R.ok(songMapper.selectList(null));
    }

    @Override
    public R songOfSingerId(Integer singerId) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id", singerId);
        return R.ok(songMapper.selectList(queryWrapper));
    }


    @Override
    public R songOfId(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return R.ok(songMapper.selectList(queryWrapper));
    }

    @Override
    public R songOfSingerName(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        List<Song> songs = songMapper.selectList(queryWrapper);
        if (songs.isEmpty()) {
            return R.fail("添加失败，没有找到该歌,无法加入该歌单");
        }

        return R.ok(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public R updateSongLrc(MultipartFile lrcFile, int id) {
        Song song = songMapper.selectById(id);
        if (lrcFile != null) {
            byte[] fileContent = new byte[0];
            try {
                fileContent = lrcFile.getBytes();
                String content = new String(fileContent, StandardCharsets.UTF_8);
                // 直接清理控制字符（包括 BOM）
                content = StringUtils.strip(content, "\uFEFF");
                song.setLyric(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (songMapper.updateById(song) > 0) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }
}
