package com.xiaou.xiaoueasyprojectbackend.module.support.novel.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.constants.CacheConsts;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.constants.DatabaseConsts;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.AuthorInfoDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.entity.AuthorInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.mapper.AuthorInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthorInfoCacheManager {

    private final AuthorInfoMapper authorInfoMapper;

    /**
     * 查询作家信息，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
        value = CacheConsts.AUTHOR_INFO_CACHE_NAME, unless = "#result == null")
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

    @CacheEvict(cacheManager = CacheConsts.REDIS_CACHE_MANAGER,
        value = CacheConsts.AUTHOR_INFO_CACHE_NAME)
    public void evictAuthorCache() {
        // 调用此方法自动清除作家信息的缓存
    }

}
