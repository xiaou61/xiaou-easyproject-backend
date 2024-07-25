package com.xiaou.xiaoueasyprojectbackend.module.support.like.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.entity.Like;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.entity.Product;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.mapper.LikeMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.service.LikeService;
import com.xiaou.xiaoueasyprojectbackend.module.support.like.service.ProductService;
import jakarta.annotation.Resource;


//@Service
public class FirstVersionLikeServiceImpl extends ServiceImpl<LikeMapper, Like>
        implements LikeService {

    @Resource
    private ProductService productService;

    @Override
    public boolean like(Integer userId, Integer productId) {
        // 查询是否有记录，如果有记录直接返回
        Like like = getOne(new QueryWrapper<Like>().lambda()
                .eq(Like::getUserId, userId)
                .eq(Like::getProductId, productId));
        if(like != null) {
            return true;
        }

        // 保存并商品点赞数加1
        save(Like.builder()
                .userId(userId)
                .productId(productId)
                .build());
        return productService.update(new UpdateWrapper<Product>().lambda()
                .setSql("like_count = like_count + 1")
                .eq(Product::getId, productId));

    }

    @Override
    public boolean unlike(Integer userId, Integer productId) {
        boolean isSuccess = remove(new QueryWrapper<Like>().lambda()
                .eq(Like::getUserId, userId)
                .eq(Like::getProductId, productId));
        if(isSuccess) {
            return productService.update(new UpdateWrapper<Product>().lambda()
                    .setSql("like_count = like_count - 1")
                    .eq(Product::getId, productId));
        }

        return true;
    }

    @Override
    public Integer getProductLikeCount(Integer productId) {
        Product product = productService.getOne(new QueryWrapper<Product>().lambda()
                .eq(Product::getId, productId), false);
        return product == null ? 0 : product.getLikeCount();
    }

    @Override
    public boolean isLiked(Integer userId, Integer productId) {
        return getOne(new QueryWrapper<Like>().lambda()
                .eq(Like::getUserId, userId)
                .eq(Like::getProductId, productId)) != null;
    }

}
