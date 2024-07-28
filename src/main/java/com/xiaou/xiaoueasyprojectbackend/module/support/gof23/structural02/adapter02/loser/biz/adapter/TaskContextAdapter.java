package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.structural02.adapter02.loser.biz.adapter;

public interface TaskContextAdapter<T> {

    TaskContext adapter(int actId, T msg);

}
