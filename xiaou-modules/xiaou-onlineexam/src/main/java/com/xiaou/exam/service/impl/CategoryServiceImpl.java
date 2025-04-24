package com.xiaou.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaou.exam.mapper.CategoryMapper;
import com.xiaou.exam.model.entity.Category;
import com.xiaou.exam.model.vo.CategoryVO;
import com.xiaou.exam.service.ICategoryService;
import com.xiaou.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.xnio.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public R<String> addCategory(Category category) {
        category.setCreateTime(new Date());
        save(category);
        return R.ok("添加分类成功");
    }

    @Override
    public R<String> updateCategory(Category category, Integer id) {
        category.setId(id);
        updateById(category);
        return R.ok("修改分类成功");
    }

    @Override
    public R<String> deleteCategory(Integer id) {
        // 检查是否有子分类
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, id);
        long count = count(wrapper);
        if (count > 0) {
            return R.fail("该分类下有子分类，不能删除");
        }

        // 检查是否有题库使用该分类
        // 这里需要添加检查题库是否使用该分类的逻辑

        removeById(id);
        return R.ok("删除分类成功");
    }

    @Override
    public R<List<CategoryVO>> getCategoryTree() {
        // 获取所有分类
        List<Category> allCategories = list();

        // 构建分类树
        List<CategoryVO> result = buildCategoryTree(allCategories, 0);

        return R.ok("获取分类树成功", result);
    }

    @Override
    public R<List<CategoryVO>> getFirstLevelCategories() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, 0)
                .orderByAsc(Category::getSort);

        List<Category> categories = list(wrapper);
        List<CategoryVO> result = categories.stream().map(this::convertToVO).collect(Collectors.toList());

        return R.ok("获取一级分类成功", result);
    }

    @Override
    public R<List<CategoryVO>> getChildCategories(Integer parentId) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, parentId)
                .orderByAsc(Category::getSort);

        List<Category> categories = list(wrapper);
        List<CategoryVO> result = categories.stream().map(this::convertToVO).collect(Collectors.toList());

        return R.ok("获取子分类成功", result);
    }


    /**
     * 构建分类树
     *
     * @param categories 所有分类
     * @param parentId 父ID
     * @return 分类树
     */
    private List<CategoryVO> buildCategoryTree(List<Category> categories, Integer parentId) {
        List<CategoryVO> result = new ArrayList<>();

        for (Category category : categories) {
            if (category.getParentId().equals(parentId)) {
                CategoryVO vo = convertToVO(category);
                vo.setChildren(buildCategoryTree(categories, category.getId()));
                result.add(vo);
            }
        }

        return result;
    }

    /**
     * 将实体转换为VO
     *
     * @param category 分类实体
     * @return 分类VO
     */
    private CategoryVO convertToVO(Category category) {
        CategoryVO vo = new CategoryVO();
        BeanUtils.copyProperties(category, vo);
        return vo;
    }
}