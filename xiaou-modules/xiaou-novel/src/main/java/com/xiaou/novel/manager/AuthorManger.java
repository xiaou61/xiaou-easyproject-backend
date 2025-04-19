package com.xiaou.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.novel.constants.DatabaseConsts;
import com.xiaou.novel.entity.dto.AuthorInfoDto;
import com.xiaou.novel.entity.po.AuthorInfo;
import com.xiaou.novel.mapper.AuthorInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorManger {

    @Resource
    private AuthorInfoMapper authorInfoMapper;

    public AuthorInfoDto getAuthor(Long userId) {
        QueryWrapper<AuthorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(DatabaseConsts.AuthorInfoTable.COLUMN_USER_ID, userId)
                .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        AuthorInfo authorInfo = authorInfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(authorInfo)) {
            return null;
        }
        return AuthorInfoDto.builder()
                .id(authorInfo.getId())
                .penName(authorInfo.getPenName())
                .status(authorInfo.getStatus()).build();
    }
}
