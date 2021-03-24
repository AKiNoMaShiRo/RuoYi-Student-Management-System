package com.ruoyi.affair.mapper;

import com.ruoyi.affair.domain.Holiday;

import java.util.List;

public interface HolidayMapper {

    /**
     * 根据学号查询节假日去向信息
     *
     * @param holiday 学号
     * @return 结果
     */
    public List<Holiday> selectHolidayById(Holiday holiday);

    /**
     * 根据辅导员工号查询节假日去向信息
     *
     * @param holiday 学号
     * @return 结果
     */
    public List<Holiday> selectHolidayByInsId(Holiday holiday);

    /**
     * 新增节假日去向报备信息
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    public int addHoliday(Holiday holiday);

    /**
     * 更新节假日去向报备信息状态
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    public int updateHolidayStatus(Holiday holiday);

    /**
     * 更新节假日去向报备信息
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    public int updateHoliday(Holiday holiday);

    /**
     * 删除节假日去向报备信息
     *
     * @param goId 节假日去向报备信息编号
     * @return 结果
     */
    public int deleteHoliday(Long goId);

}
