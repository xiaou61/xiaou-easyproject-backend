package com.xiaou.xiaoueasyprojectbackend.module.support.home;

import com.xiaou.xiaoueasyprojectbackend.common.utils.ResultUtils;
import com.xiaou.xiaoueasyprojectbackend.module.support.login.domain.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public BaseResponse index(){
        return ResultUtils.success("欢迎来到xiaou-easyproject(如果你能看见这段文字 说明你的后端已经部署成功了)");
    }
}
