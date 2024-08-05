package com.xiaou.xiaoueasyprojectbackend.module.support.auth.service;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.UserSessionInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.vo.SysUserVO;

public interface IUserService {
    /**
     * 通过邮箱查询用户
     *
     * @param currentUser 当前登录用户
     * @return 视图对象
     */
    SysUserVO selectSysUserInfo(UserSessionInfo currentUser);
    
    /**
     * 通过邮箱更新用户
     *
     * @param sysUserVO
     * @return
     */
    String updateSysUserInfo(SysUserVO sysUserVO);
}
