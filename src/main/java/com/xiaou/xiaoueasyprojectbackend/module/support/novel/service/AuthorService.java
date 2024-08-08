package com.xiaou.xiaoueasyprojectbackend.module.support.novel.service;

import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.AuthorRegisterReqDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.resp.RestResp;

/**
 * 作家模块 业务服务类
 *
 * @author xiongxiaoyang
 * @date 2022/5/23
 */
public interface AuthorService {

    /**
     * 作家注册
     *
     * @param dto 注册参数
     * @return void
     */
    RestResp<Void> register(AuthorRegisterReqDto dto);

    /**
     * 查询作家状态
     *
     * @param userId 用户ID
     * @return 作家状态
     */
    RestResp<Integer> getStatus(Long userId);
}
