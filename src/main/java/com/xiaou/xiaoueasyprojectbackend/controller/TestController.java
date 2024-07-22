package com.xiaou.xiaoueasyprojectbackend.controller;

import com.xiaou.xiaoueasyprojectbackend.common.core.domain.R;
import com.xiaou.xiaoueasyprojectbackend.common.utils.RUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "测试接口")
@RestController
public class TestController {
    @Operation(description = "测试接口")
    @GetMapping
    public R test() {
        return RUtils.success("如果你看到这个就为测试成功");
    }
}
