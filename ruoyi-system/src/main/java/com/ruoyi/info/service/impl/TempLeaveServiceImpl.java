package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.TempLeave;
import com.ruoyi.info.mapper.TempLeaveMapper;
import com.ruoyi.info.service.ITempLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempLeaveServiceImpl implements ITempLeaveService {

    @Autowired
    private TempLeaveMapper tempLeaveMapper;

    /**
     * 根据学号查询请假信息
     *
     * @param studentId 学号
     * @return 结果
     */
    @Override
    public List<TempLeave> selectLeaveById(String studentId){
        return tempLeaveMapper.selectLeaveById(studentId);
    }
}
