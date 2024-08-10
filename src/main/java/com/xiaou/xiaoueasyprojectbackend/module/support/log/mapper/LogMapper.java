package com.xiaou.xiaoueasyprojectbackend.module.support.log.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaou.xiaoueasyprojectbackend.module.support.log.entity.Log;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author xiaohai
 * @since 2023-03-30
 */
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 清空表数据
     * @return
     */
    int truncateTable();

    /**
     * 统计最近七天内的访问量(PV)
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @MapKey("date")
    List<Map<String, Object>> getPVByWeek(@Param("startTime")String startTime, @Param("endTime")String endTime);

    /**
     * 获得最近七天的接口访问量
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @MapKey("date")
    List<Map<String, Object>> getRCByWeek(@Param("startTime")String startTime, @Param("endTime")String endTime);

    /**
     * 获得最近七天的独立用户
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @MapKey("date")
    List<Map<String, Object>> getUVByWeek(@Param("startTime")String startTime, @Param("endTime")String endTime);


}
