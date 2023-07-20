package com.xiaou.xiaoueasyprojectbackend.module.support.question.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.form.QuestionFrom;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity.Question;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo.QuestionVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface IQuestionService extends IService<Question> {

    /**
     * 单题添加
     *
     * @param questionFrom 传参
     * @return 响应
     */
    com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result<String> addSingleQuestion(QuestionFrom questionFrom);

    /**
     * 批量删除试题
     *
     * @param ids 试题id
     * @return 响应
     */
    com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result<String> deleteBatchByIds(String ids);


    /**
     * 分页查询试题
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param content  试题名
     * @param type     试题类型
     * @param repoId   题库id
     * @return 响应
     */
    com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result<IPage<QuestionVO>> pagingQuestion(Integer pageNum, Integer pageSize, String content, Integer type, Integer repoId);

    /**
     * 根据试题id获取单题详情
     *
     * @param id 试题id
     * @return 结果集
     */
    com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result<QuestionVO> querySingle(Integer id);

    /**
     * 修改试题
     *
     * @param questionFrom 需要修改的试题
     * @return 结果
     */
    com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result<String> updateQuestion(QuestionFrom questionFrom);


}