package com.xiaou.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.BatchDeleteRequest;
import com.xiaou.exam.model.req.QuestionFrom;
import com.xiaou.exam.model.vo.QuestionVO;
import com.xiaou.model.page.PageReqDto;
import com.xiaou.model.page.PageRespDto;
import com.xiaou.utils.R;
import org.springframework.web.multipart.MultipartFile;

public interface IQuestionService extends IService<Question> {
    /**
     * 单题添加
     *
     * @param questionFrom 传参
     * @return 响应
     */
    R<String> addSingleQuestion(QuestionFrom questionFrom);


    R<String> deleteBatchByIds(BatchDeleteRequest req);

    R<PageRespDto<Question>> pageQuestion(PageReqDto req);

    /**
     * 根据试题id获取单题详情
     *
     * @param id 试题id
     * @return 结果集
     */
    R<QuestionVO> querySingle(Integer id);

    /**
     * 修改试题
     *
     * @param questionFrom 需要修改的试题
     * @return 结果
     */
    R<String> updateQuestion(QuestionFrom questionFrom);

    R<String> importQuestion(Integer id, MultipartFile file);
}
