package com.xiaou.xiaoueasyprojectbackend.common.core.domain;

import lombok.Data;


@Data
public class R<T> {
/*返回体*/
private  Integer code;
private String msg;
private T data;

}
