package com.xiaou.hot.controller;

import com.xiaou.hot.model.vo.HotPostVO;
import com.xiaou.hot.service.HotPostService;
import com.xiaou.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hot")
@Slf4j
@RequiredArgsConstructor
//@Api(tags = "热榜数据")
public class HotPostController {

    private final HotPostService hotPostService;

    /**
     * 获取列表（封装类）
     */
    @PostMapping("/list")
    @Operation(summary = "获取列表（封装类）")
    public R<List<HotPostVO>> getHotPostList() {
        return R.ok(hotPostService.getHotPostList());
    }


}