package com.ruoyi.info.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuInfo;

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
}
