package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.core;


public class TerminalExpression implements Expression {

    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.equals(data);
    }

}