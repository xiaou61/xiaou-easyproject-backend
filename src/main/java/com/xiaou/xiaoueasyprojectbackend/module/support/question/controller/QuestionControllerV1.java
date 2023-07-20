package com.xiaou.xiaoueasyprojectbackend.module.support.question.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.form.QuestionFrom;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.group.QuestionGroup;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo.QuestionVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.result.Result;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.service.IQuestionService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/v1/question")
public class QuestionControllerV1 {
    @Resource
    private IQuestionService iQuestionService;

    /**
     * 单题添加
     * @param questionFrom 传参
     * @return 响应添加示例：
     * {
     *     "quType": 1,
     *     "content": "dolor id",
     *     "repoId": 32,
     *     "id": 15,
     *     "image": "http://dummyimage.com/400x400",
     *     "options": [
     *         {
     *             "isDeleted": 0,
     *             "quId": 51,
     *             "isRight": 1,
     *             "content": 123,
     *             "id": 75,
     *             "sort": 58,
     *             "image": "http://dummyimage.com/400x400"
     *         },
     *           {
     *             "isDeleted": 0,
     *             "quId": 52,
     *             "isRight": 0,
     *             "content": 456,
     *             "id": 75,
     *             "sort": 59,
     *             "image": "http://dummyimage.com/400x400"
     *         }
     *     ],
     *     "createTime": "2024-07-26T15:30:00",
     *     "analysis": null
     * }
     */
    @PostMapping("/single")
    public Result<String> addSingleQuestion(@Validated(QuestionGroup.QuestionAddGroup.class) @RequestBody QuestionFrom questionFrom) {
        return iQuestionService.addSingleQuestion(questionFrom);
    }

    /**
     * 批量删除试题
     * @param ids 试题id
     * @return 相应
     */
    @DeleteMapping("/batch/{ids}")
    public Result<String> deleteBatchQuestion(@PathVariable("ids") String ids) {
        return iQuestionService.deleteBatchByIds(ids);
    }

    /**
     * 分页查询试题
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param content  试题名
     * @param repoId   题库id
     * @param type     试题类型
     * @return 响应
     */
    @GetMapping("/paging")
    public Result<IPage<QuestionVO>> pagingQuestion(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                    @RequestParam(value = "content", required = false) String content,
                                                    @RequestParam(value = "repoId", required = false) Integer repoId,
                                                    @RequestParam(value = "type", required = false) Integer type) {
        return iQuestionService.pagingQuestion(pageNum, pageSize, content, type, repoId);
    }

    /**
     * 根据试题id获取单题详情
     * @param id 试题id
     * @return 响应结果
     */
    @GetMapping("/single/{id}")
    public Result<QuestionVO> querySingle(@PathVariable("id") Integer id) {
        return iQuestionService.querySingle(id);
    }

    /**
     * 修改试题
     * @param id           试题Id
     * @param questionFrom 入参
     * @return 响应结果
     */
    @PutMapping("/{id}")
    public Result<String> updateQuestion(@PathVariable("id") Integer id, @RequestBody QuestionFrom questionFrom) {
        questionFrom.setId(id);
        return iQuestionService.updateQuestion(questionFrom);
    }


}
