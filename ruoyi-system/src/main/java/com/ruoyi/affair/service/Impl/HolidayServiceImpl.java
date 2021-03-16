package com.ruoyi.affair.service.Impl;

import com.ruoyi.affair.domain.Holiday;
import com.ruoyi.affair.mapper.HolidayMapper;
import com.ruoyi.affair.service.IHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements IHolidayService {

    @Autowired
    private HolidayMapper holidayMapper;

    /**
     * 根据学号查询节假日去向信息
     *
     * @param studentId 学号
     * @return 结果
     */
    @Override
    public List<Holiday> selectHolidayById(String studentId){
        return holidayMapper.selectHolidayById(studentId);
    }

    /**
     * 根据辅导员查询节假日去向信息
     *
     * @param instructorId 学号
     * @return 结果
     */
    public List<Holiday> selectHolidayByInsId(String instructorId){
        return holidayMapper.selectHolidayByInsId(instructorId);
    }

    /**
     * 新增节假日去向报备信息
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    @Override
    public int addHoliday(Holiday holiday){
        return holidayMapper.addHoliday(holiday);
    }

    /**
     * 更新节假日去向报备信息状态
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    @Override
    public int updateHolidayStatus(Holiday holiday){
        return holidayMapper.updateHolidayStatus(holiday);
    }

    /**
     * 修改节假日去向报备信息
     *
     * @param holiday 节假日去向报备
     * @return 结果
     */
    public int updateHoliday(Holiday holiday){
        return holidayMapper.updateHoliday(holiday);
    }

    /**
     * 删除节假日去向报备信息
     *
     * @param goId 节假日去向报备信息编号
     * @return 结果
     */
    public int deleteHoliday(Long goId){
        return holidayMapper.deleteHoliday(goId);
    }

}
