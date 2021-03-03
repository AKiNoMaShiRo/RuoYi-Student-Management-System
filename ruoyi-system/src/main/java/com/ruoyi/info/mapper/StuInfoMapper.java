package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.StuInfo;

public interface StuInfoMapper {

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    public StuInfo selectStuById(String studentId);
}
