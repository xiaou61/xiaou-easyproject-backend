package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.template01.loser.biz;

public abstract class BaseAwardHandler {

    private boolean checkUser(long userId) {
        // 检验用户合法性
        return userId > 0;
        // 检验用户是否存在
    }

    private void logAward(long userId, AwardConfig config) {
        System.out.println("logAward userId:" + userId + " award:" + config);
    }

    /**
     * 按照模板顺序执行奖励逻辑 只暴露核心不同业务点给子类实现
     */
    public void doAward(long userId, AwardConfig awardConfig) {

        // 01 检验用户
        if (!checkUser(userId)) {
            return;
        }

        // 02 发送奖励
        doAwardCore(userId, awardConfig);

        // 03 写入日志
        logAward(userId, awardConfig);

        // 04 发送奖励通知
        awardNotice(userId, awardConfig);

    }

    private void awardNotice(long userId, AwardConfig config) {
        System.out.println("awardNotice userId:" + userId + " award:" + config);
        System.out.println();
        System.out.println();
    }

    /**
     * 留给不同的子类实现
     */
    protected abstract void doAwardCore(Long userId, AwardConfig config);

    /**
     * 获取奖励类型（策略模式）
     */
    protected abstract int getAwardType();

}
