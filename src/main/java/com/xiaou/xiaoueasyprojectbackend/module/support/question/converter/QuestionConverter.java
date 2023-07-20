package com.xiaou.xiaoueasyprojectbackend.module.support.question.converter;

import com.xiaou.xiaoueasyprojectbackend.module.support.question.form.QuestionFrom;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.entity.Question;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo.QuestionSheetVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.question.model.vo.QuestionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface QuestionConverter {

    @Mapping(target = "repoId",source = "repoId")
    Question fromToEntity(QuestionFrom questionFrom);

    List<QuestionSheetVO> listEntityToVO(List<Question> questions);

    @Mapping(target = "quId",source = "id")
    QuestionSheetVO entityToVO(Question question);

    QuestionVO QuestionToQuestionVO(Question question);
}
