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
        return tempLeaveMapper.selectLeaveByStuId(studentId);
    }

    /**
     * 新增请假信息
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int insertTempLeave(TempLeave tempLeave){
        return tempLeaveMapper.insertTempLeave(tempLeave);
    }

    /**
     * 删除请假信息
     *
     * @param leaveId 请假表信息编号
     * @return 结果
     */
    public int deleteTempLeave(Long leaveId){
        return tempLeaveMapper.deleteTempLeave(leaveId);
    }

    /**
     * 修改请假信息状态
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int updateStatus(TempLeave tempLeave){
        return tempLeaveMapper.updateStatus(tempLeave);
    }

    /**
     * 修改请假申请
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int updateTempLeave(TempLeave tempLeave){
        return tempLeaveMapper.updateTempLeave(tempLeave);
    }
}
