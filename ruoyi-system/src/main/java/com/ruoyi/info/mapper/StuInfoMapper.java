package com.ruoyi.info.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuBaseInfo;
import com.ruoyi.info.domain.StuInfo;
import com.ruoyi.info.domain.StuUserInfo;

import java.util.List;

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

    /**
     * 批量查询学生基本信息
     *
     * @param stuBaseInfo 入参
     * @return 学生信息
     */
    public List<StuBaseInfo> selectAllStuBase(StuBaseInfo stuBaseInfo);

    /**
     * 添加学生账户
     *
     * @param stuUserInfo 学生信息
     * @return 结果
     */
    public int insertStuUserInfo(StuUserInfo stuUserInfo);

    /**
     * 添加学生基本信息
     *
     * @param stuBaseInfo 学生基本信息
     * @return 结果
     */
    public int insertStuBaseInfo(StuBaseInfo stuBaseInfo);

}
