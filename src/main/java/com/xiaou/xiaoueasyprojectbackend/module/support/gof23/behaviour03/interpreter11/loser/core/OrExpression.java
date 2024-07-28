package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.interpreter11.loser.core;

public class OrExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(String context) {
        return left.interpret(context) || right.interpret(context);
    }

}