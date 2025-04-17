package com.xiaou.music.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "u_singer")
@Data
public class Singer {

    @TableId(type = IdType.ASSIGN_UUID)
    private Integer id;

    private String name;

    private Byte sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}