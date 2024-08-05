package com.xiaou.xiaoueasyprojectbackend.module.support.auth.convert;

import com.xiaou.xiaoueasyprojectbackend.module.support.auth.entity.SysUser;
import com.xiaou.xiaoueasyprojectbackend.module.support.auth.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);
    
    /**
     * 将SysUser转换成SysUserVO
     *
     * @param sysUser 用户实体对象
     * @return 用户视图对象
     */
    @Mappings({
            @Mapping(source = "userId", target = "userId")
    })
    SysUserVO sysUserToVO(SysUser sysUser);
    
    /**
     * 将SysUserList转换成SysUserVOList
     *
     * @param sysUserList 用户实体对象列表
     * @return 用户视图对象列表
     */
    List<SysUserVO> sysUserListToVOList(List<SysUser> sysUserList);
    
    /**
     * 将SysUserVO转换成SysUser
     *
     * @param sysUserVO 用户视图对象
     * @return 用户实体对象
     */
    @Mappings({
            @Mapping(source = "userId", target = "userId")
    })
    SysUser sysUserVOToDAO(SysUserVO sysUserVO);
    
    
}
