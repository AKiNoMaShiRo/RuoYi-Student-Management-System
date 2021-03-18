package com.ruoyi.info.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuBaseInfo;
import com.ruoyi.info.domain.StuInfo;
import com.ruoyi.info.domain.StuUserInfo;
import com.ruoyi.system.domain.SysUserRole;

import java.util.List;

/**
 * 学生信息 业务层
 *
 * @author akino
 */
public interface IStuInfoService {
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

//    /**
//     * 修改学生基本信息
//     *
//     * @param stuBaseInfo 学生信息
//     * @return 结果
//     */
//    public int updateStuBase(StuBaseInfo stuBaseInfo);

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
     * 添加学生基本信息
     *
     * @param baseInfo 入参
     * @return 结果
     */
    public int insertStuBaseInfo(StuBaseInfo baseInfo);

    /**
     * 添加学生角色信息
     *
     * @param studentId 学生学号
     * @return 结果
     */
    public int insertStuRoleInfo(String studentId);

    /**
     * 删除学生基本信息
     *
     * @param studentId 学生学号
     * @return 结果
     */
    public int deleteStuBaseInfo(String studentId);

    /**
     * 删除学生角色信息
     *
     * @param userId 学生学号
     * @return 结果
     */
    public int deleteStuRoleInfo(Long userId);

    /**
     * 删除学生用户信息
     *
     * @param userId 学生用户ID
     * @return 结果
     */
    public int deleteStuUserInfo(Long userId);

}
