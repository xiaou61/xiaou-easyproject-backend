package com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.controller;




import com.github.pagehelper.PageInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.entity.Notice;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.entity.Result;
import com.xiaou.xiaoueasyprojectbackend.module.support.noticev3.service.NoticeServiceV3;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author xiaou61
 * @Date 2024/8/05 20:03
 * @Description: 公告功能实现 V3 这个相比于前俩个属于是简单版本的
 */
@RestController
@RequestMapping("/notice")
@Tag(name = "公告功能实现 V3")
public class NoticeController {

    @Resource
    private NoticeServiceV3 noticeServiceV3;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        noticeServiceV3.add(notice);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeServiceV3.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        noticeServiceV3.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeServiceV3.updateById(notice);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeServiceV3.selectById(id);
        return Result.success(notice);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice ) {
        List<Notice> list = noticeServiceV3.selectAll(notice);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notice> page = noticeServiceV3.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }

}