package com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ProdCommDataDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.dto.ProdCommDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.ProdComm;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.mapper.ProdCommMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.ProdCommService;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.util.Arith;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProdCommServiceImpl extends ServiceImpl<ProdCommMapper, ProdComm> implements ProdCommService {

    @Resource
    private ProdCommMapper prodCommMapper;

    @Override
    public ProdCommDataDto getProdCommDataByProdId(Long prodId) {
        ProdCommDataDto prodCommDataDto = prodCommMapper.getProdCommDataByProdId(prodId);
        //计算出好评率
        if (prodCommDataDto.getPraiseNumber() == 0 || prodCommDataDto.getNumber() == 0) {
            prodCommDataDto.setPositiveRating(0.0);
        } else {
            prodCommDataDto.setPositiveRating(Arith.mul(Arith.div(prodCommDataDto.getPraiseNumber(), prodCommDataDto.getNumber()), 100));
        }
        return prodCommDataDto;
    }

    @Override
    public IPage<ProdCommDto> getProdCommDtoPageByUserId(Page page, String userId) {
        return prodCommMapper.getProdCommDtoPageByUserId(page, userId);
    }

    @Override
    public IPage<ProdCommDto> getProdCommDtoPageByProdId(Page page, Long prodId, Integer evaluate) {

        IPage<ProdCommDto> prodCommDtos = prodCommMapper.getProdCommDtoPageByProdId(page, prodId, evaluate);
        prodCommDtos.getRecords().forEach(prodCommDto -> {
            // 匿名评价
            if (prodCommDto.getIsAnonymous() == 1) {
                prodCommDto.setNickName(null);
            }
        });
        return prodCommDtos;
    }

    @Override
    /**
     * 这个方法需要自己去完善 我这里就不再完善了
     */
    public IPage<ProdComm> getProdCommPage(Page page, ProdComm prodComm) {
        return prodCommMapper.getProdCommPage(page, prodComm);
    }
}