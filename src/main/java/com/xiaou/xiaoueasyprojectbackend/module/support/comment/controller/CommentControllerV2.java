package com.xiaou.xiaoueasyprojectbackend.module.support.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.entity.ProdComm;
import com.xiaou.xiaoueasyprojectbackend.module.support.comment.service.ProdCommService;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.entity.PageParam;
import com.xiaou.xiaoueasyprojectbackend.module.support.notice.resp.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author xiaou61
 * @Date 2024/7/28 15:25
 * @Description 商品评论功能实现
 */
@RestController
@RequestMapping("/v2/comment")
@Tag(name = "商品评论功能V2", description = "商品评论功能实现")
public class CommentControllerV2 {


    @Resource
    private ProdCommService prodCommService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param prodComm 商品评论
     * @return 分页数据
     */
    @GetMapping("/page")
    public ServerResponseEntity<IPage<ProdComm>> getProdCommPage(PageParam page, ProdComm prodComm) {
        return ServerResponseEntity.success(prodCommService.getProdCommPage(page, prodComm));
    }


    /**
     * 通过id查询商品评论
     *
     * @param prodCommId id
     * @return 单个数据
     */
    @GetMapping("/info/{prodCommId}")
    public ServerResponseEntity<ProdComm> getById(@PathVariable("prodCommId") Long prodCommId) {
        return ServerResponseEntity.success(prodCommService.getById(prodCommId));
    }

    /**
     * 新增商品评论
     *
     * @param prodComm 商品评论
     * @return 是否新增成功
     */
    @PostMapping
    public ServerResponseEntity<Boolean> save(@RequestBody @Valid ProdComm prodComm) {
        return ServerResponseEntity.success(prodCommService.save(prodComm));
    }

    /**
     * 修改商品评论
     *
     * @param prodComm 商品评论
     * @return 是否修改成功
     */
    @PutMapping
    public ServerResponseEntity<Boolean> updateById(@RequestBody @Valid ProdComm prodComm) {
        prodComm.setReplyTime(new Date());
        return ServerResponseEntity.success(prodCommService.updateById(prodComm));
    }

    /**
     * 通过id删除商品评论
     *
     * @param prodCommId id
     * @return 是否删除成功
     */
    @DeleteMapping("/{prodCommId}")
    public ServerResponseEntity<Boolean> removeById(@PathVariable Long prodCommId) {
        return ServerResponseEntity.success(prodCommService.removeById(prodCommId));
    }
}
