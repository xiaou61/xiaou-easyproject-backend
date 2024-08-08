package com.xiaou.xiaoueasyprojectbackend.module.support.novel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.BookInfoRespDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.dto.BookSearchReqDto;
import com.xiaou.xiaoueasyprojectbackend.module.support.novel.entity.BookInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BookInfoMapper extends BaseMapper<BookInfo> {

    /**
     * 增加小说点击量
     *
     * @param bookId 小说ID
     */
    void addVisitCount(@Param("bookId") Long bookId);

    /**
     * 小说搜索
     * @param page mybatis-plus 分页对象
     * @param condition 搜索条件
     * @return 返回结果
     * */
    List<BookInfo> searchBooks(IPage<BookInfoRespDto> page, BookSearchReqDto condition);

}
