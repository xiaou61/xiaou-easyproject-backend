package com.xiaou.xiaoueasyprojectbackend.module.support.novel.service.impl;

import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.AuthorInfoDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.AuthorRegisterReqDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.entity.AuthorInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.manager.AuthorInfoCacheManager;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.mapper.AuthorInfoMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.resp.RestResp;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.service.AuthorService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service

@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private AuthorInfoCacheManager authorInfoCacheManager;

    @Resource
    private AuthorInfoMapper authorInfoMapper;

    @Override
    public RestResp<Void> register(AuthorRegisterReqDto dto) {
        // 校验该用户是否已注册为作家
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(dto.getUserId());
        if (Objects.nonNull(author)) {
            // 该用户已经是作家，直接返回
            return RestResp.ok();
        }
        // 保存作家注册信息
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setUserId(dto.getUserId());
        authorInfo.setChatAccount(dto.getChatAccount());
        authorInfo.setEmail(dto.getEmail());
        authorInfo.setInviteCode("0");
        authorInfo.setTelPhone(dto.getTelPhone());
        authorInfo.setPenName(dto.getPenName());
        authorInfo.setWorkDirection(dto.getWorkDirection());
        authorInfo.setCreateTime(LocalDateTime.now());
        authorInfo.setUpdateTime(LocalDateTime.now());
        authorInfoMapper.insert(authorInfo);
        // 清除作家缓存
        authorInfoCacheManager.evictAuthorCache();
        return RestResp.ok();
    }

    @Override
    public RestResp<Integer> getStatus(Long userId) {
        AuthorInfoDto author = authorInfoCacheManager.getAuthor(userId);
        return Objects.isNull(author) ? RestResp.ok(null) : RestResp.ok(author.getStatus());
    }
}
