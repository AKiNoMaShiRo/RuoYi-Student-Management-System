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
}
