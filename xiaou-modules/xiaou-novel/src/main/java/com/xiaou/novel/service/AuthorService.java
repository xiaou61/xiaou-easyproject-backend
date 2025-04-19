package com.xiaou.novel.service;

import com.xiaou.novel.entity.req.AuthorRegisterReqDto;
import com.xiaou.utils.R;

public interface AuthorService {

    /**
     * 作家注册
     *
     * @param dto 注册参数
     * @return void
     */
    R<Void> register(AuthorRegisterReqDto dto);

    /**
     * 查询作家状态
     *
     * @param userId 用户ID
     * @return 作家状态
     */
    R<Integer> getStatus(Long userId);
}
