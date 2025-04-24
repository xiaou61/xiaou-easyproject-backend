package com.xiaou.exam.converter;

import com.xiaou.exam.model.entity.Question;
import com.xiaou.exam.model.req.QuestionFrom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface QuestionConverter {

// 将QuestionFrom对象转换为Question对象
    @Mapping(target = "repoId",source = "repoId")
    Question fromToEntity(QuestionFrom questionFrom);

}
