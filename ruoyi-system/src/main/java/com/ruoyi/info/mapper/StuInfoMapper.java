package com.ruoyi.info.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuBaseInfo;
import com.ruoyi.info.domain.StuInfo;

public interface StuInfoMapper {

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    public StuInfo selectStuById(String studentId);

    /**
     * 修改学生信息
     *
     * @param stuInfo 学生信息
     * @return 结果
     */
    public int updateStu(StuInfo stuInfo);

    /**
     * 根据学生学号查询学生基本信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    public StuBaseInfo selectStuBaseById(String studentId);
}
