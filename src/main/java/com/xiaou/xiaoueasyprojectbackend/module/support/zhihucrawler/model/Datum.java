
package com.xiaou.xiaoueasyprojectbackend.module.support.zhihucrawler.model;

import lombok.Data;

import java.util.List;


@Data
public class Datum {

    private String attachedInfo;
    private String cardId;
    private List<Child> children;
    private Boolean debut;
    private String detailText;
    private String id;
    private String styleType;
    private Target target;
    private Long trend;
    private String type;

}
