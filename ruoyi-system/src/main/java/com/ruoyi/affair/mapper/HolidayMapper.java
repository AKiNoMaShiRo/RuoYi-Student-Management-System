package com.ruoyi.affair.mapper;

import com.ruoyi.affair.domain.Holiday;

import java.util.List;

public interface HolidayMapper {

    /**
     * 根据学号查询节假日去向信息
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<Holiday> selectHolidayById(String studentId);
}
