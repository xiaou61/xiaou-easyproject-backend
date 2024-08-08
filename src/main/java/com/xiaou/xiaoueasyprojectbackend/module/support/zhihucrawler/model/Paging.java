
package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;


import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Paging {

    private Boolean isEnd;
    private String next;
    private String previous;

}
