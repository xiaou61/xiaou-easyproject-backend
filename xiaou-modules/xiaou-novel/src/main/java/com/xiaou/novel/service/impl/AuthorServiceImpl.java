package com.xiaou.novel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.novel.constants.DatabaseConsts;
import com.xiaou.novel.entity.dto.AuthorInfoDto;
import com.xiaou.novel.entity.po.AuthorInfo;
import com.xiaou.novel.entity.req.AuthorRegisterReqDto;
import com.xiaou.novel.manager.AuthorManger;
import com.xiaou.novel.mapper.AuthorInfoMapper;
import com.xiaou.novel.service.AuthorService;
import com.xiaou.utils.R;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private AuthorInfoMapper authorInfoMapper;

    @Resource
    private AuthorManger authorManger;


    @Override
    public R<Void> register(AuthorRegisterReqDto dto) {

        // 校验该用户是否已注册为作家
        AuthorInfoDto author = authorManger.getAuthor(dto.getUserId());
        if (Objects.nonNull(author)) {
            // 该用户已经是作家，直接返回
            return R.ok("该用户已经是作家");
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
        return R.ok();
    }

    @Override
    public R<Integer> getStatus(Long userId) {
        AuthorInfoDto author = authorManger.getAuthor(userId);
        return Objects.isNull(author) ? R.ok(null) : R.ok(author.getStatus());
    }


}
