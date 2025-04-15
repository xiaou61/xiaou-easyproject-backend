package com.xiaou.web.controller;

import com.xiaou.utils.R;
import com.xiaou.web.domain.User;
import com.xiaou.web.mapper.LoginMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Resource
    private LoginMapper loginMapper;

    //todo 登录功能之后集成sa-token再进行优化
    @PostMapping("/login")
    public R<String> login(@RequestBody User user) {
        User dbUser = loginMapper.selectById(1);
        if (dbUser == null) {
            return R.fail("用户不存在");
        }
        if (Objects.equals(dbUser.getPassword(), user.getPassword())) {
            return R.ok("登录成功");
        } else {
            return R.fail("密码错误");
        }
    }

}
