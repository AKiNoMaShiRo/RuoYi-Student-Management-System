package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.TempLeave;

import java.util.List;

public interface TempLeaveMapper {
    /**
     * 根据学号查询请假信息
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<TempLeave> selectLeaveById(String studentId);
}
