package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.creational01.prototype04.loser.core;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Question implements Cloneable {

    private String question;

    private List<Answer> answers;

    @Override
    public Question clone() {
        try {
            Question clone = (Question) super.clone();
            clone.setAnswers(answers.stream().map(Answer::clone).collect(Collectors.toList()));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
