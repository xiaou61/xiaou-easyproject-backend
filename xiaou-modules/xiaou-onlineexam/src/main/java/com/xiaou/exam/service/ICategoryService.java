package com.xiaou.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.exam.model.entity.Category;
import com.xiaou.exam.model.vo.CategoryVO;
import com.xiaou.utils.R;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    /**
     * 添加分类
     *
     * @param category 分类信息
     * @return 结果
     */
    R<String> addCategory(Category category);

    /**
     * 修改分类
     *
     * @param category 分类信息
     * @param id 分类ID
     * @return 结果
     */
    R<String> updateCategory(Category category, Integer id);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 结果
     */
    R<String> deleteCategory(Integer id);

    /**
     * 获取分类树
     *
     * @return 分类树
     */
    R<List<CategoryVO>> getCategoryTree();

    /**
     * 获取一级分类列表
     *
     * @return 一级分类列表
     */
    R<List<CategoryVO>> getFirstLevelCategories();

    /**
     * 根据父ID获取子分类
     *
     * @param parentId 父ID
     * @return 子分类列表
     */
    R<List<CategoryVO>> getChildCategories(Integer parentId);

}
