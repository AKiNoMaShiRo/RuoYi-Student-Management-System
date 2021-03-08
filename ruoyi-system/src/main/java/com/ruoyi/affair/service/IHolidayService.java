package com.ruoyi.affair.service;

import com.ruoyi.affair.domain.Holiday;

import java.util.List;

/**
 * 节假日去向 业务层
 *
 * @author akino
 */
public interface IHolidayService {
    /**
     * 根据学号查询节假日去向信息
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<Holiday> selectHolidayById(String studentId);
}
