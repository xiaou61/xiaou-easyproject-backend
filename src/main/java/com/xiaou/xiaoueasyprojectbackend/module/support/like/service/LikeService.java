package com.xiaou.xiaoueasyprojectbackend.module.support.like.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.entity.Like;


public interface LikeService extends IService<Like> {

    boolean like(Integer userId, Integer productId);

    boolean unlike(Integer userId, Integer productId);

    Integer getProductLikeCount(Integer productId);

    boolean isLiked(Integer userId, Integer productId);
}
