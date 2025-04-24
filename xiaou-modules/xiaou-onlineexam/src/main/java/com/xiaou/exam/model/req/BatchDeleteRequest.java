package com.xiaou.exam.model.req;

import lombok.Data;

import java.util.List;
@Data
public class BatchDeleteRequest {
    private List<Integer> ids;
}
