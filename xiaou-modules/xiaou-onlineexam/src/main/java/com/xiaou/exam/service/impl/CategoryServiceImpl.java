package com.xiaou.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.exam.mapper.CategoryMapper;
import com.xiaou.exam.model.entity.Category;
import com.xiaou.exam.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}