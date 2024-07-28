package com.xiaou.xiaoueasyprojectbackend.module.support.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ProdCommDataDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ProdCommDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.ProdComm;
import org.apache.ibatis.annotations.Param;
public interface ProdCommMapper extends BaseMapper<ProdComm> {
    /**
     * 根据商品id获取商品评论信息
     * @param prodId
     * @return
     */
    ProdCommDataDto getProdCommDataByProdId(@Param("prodId") Long prodId);

    /**
     * 根据评价等级和商品id分页获取商品评价
     * @param page
     * @param prodId
     * @param evaluate
     * @return
     */
    IPage<ProdCommDto> getProdCommDtoPageByProdId(@Param("page") Page page, @Param("prodId") Long prodId, @Param("evaluate") Integer evaluate);

    /**
     * 根据用户id分页获取评论列表
     * @param page
     * @param userId
     * @return
     */
    IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page, @Param("userId") String userId);

    /**
     * 根据参数分页获取商品评论
     * @param page
     * @param prodComm
     * @return
     */
    IPage<ProdComm> getProdCommPage(Page page, @Param("prodComm") ProdComm prodComm);
}