package com.ruoyi.affair.service;

import com.ruoyi.affair.domain.TempLeave;

import java.util.List;

public interface ITempLeaveService {
    /**
     * 根据学号查询请假信息
     *
     * @param tempLeave 学号
     * @return 结果
     */
    public List<TempLeave> selectLeaveById(TempLeave tempLeave);

    /**
     * 根据辅导员工号查询请假信息
     *
     * @param tempLeave 学号
     * @return 结果
     */
    public List<TempLeave> selectLeaveByInstructor(TempLeave tempLeave);

    /**
     * 新增请假信息
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int insertTempLeave(TempLeave tempLeave);

    /**
     * 删除请假信息
     *
     * @param leaveId 请假表信息编号
     * @return 结果
     */
    public int deleteTempLeave(Long leaveId);

    /**
     * 修改请假信息状态
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int updateStatus(TempLeave tempLeave);

    /**
     * 修改请假申请
     *
     * @param tempLeave 请假表信息
     * @return 结果
     */
    public int updateTempLeave(TempLeave tempLeave);
}
