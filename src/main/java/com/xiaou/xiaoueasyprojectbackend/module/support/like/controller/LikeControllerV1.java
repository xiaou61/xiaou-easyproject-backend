package com.xiaou.xiaoueasyprojectbackend.module.support.like.controller;

import com.xiaou.xiaoueasyprojectbackend.module.support.like.dto.LikeDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.service.LikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaou61
 * @Date 2024/7/25 19:27
 * @Description: 点赞功能实现：多种实现Impl实现方式 最推荐使用的是redis版本的
 */
@Tag(name = "点赞功能实现V1", description = "点赞功能实现")
@RestController
@RequestMapping("/v1/like")
public class LikeControllerV1 {

    @Resource
    private LikeService likeService;

    @PostMapping("like")
    public boolean like(@RequestBody LikeDto likeDto) {
        return likeService.like(likeDto.userId, likeDto.getProductId());
    }

    @PostMapping("unlike")
    public boolean unlike(@RequestBody LikeDto likeDto) {
        return likeService.unlike(likeDto.userId, likeDto.getProductId());
    }

    @GetMapping("getProductLikeCount")
    public Integer getProductLikeCount(@RequestParam Integer productId) {
        return likeService.getProductLikeCount(productId);
    }

    @GetMapping("isLike")
    public boolean isLiked(@RequestBody LikeDto likeDto) {
        return likeService.isLiked(likeDto.getUserId(), likeDto.getProductId());
    }
}
