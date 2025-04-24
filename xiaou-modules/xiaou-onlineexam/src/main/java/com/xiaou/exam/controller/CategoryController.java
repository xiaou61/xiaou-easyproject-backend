package com.xiaou.exam.controller;


import com.xiaou.exam.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "题库分类管理相关接口")
@RestController
@RequestMapping("/exam/category")
public class CategoryController {
    @Resource
    private ICategoryService categoryService;
}
