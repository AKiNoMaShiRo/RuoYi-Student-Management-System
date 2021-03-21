package com.ruoyi.grade.mapper;

import com.ruoyi.grade.domain.MultipGrade;

import java.util.List;

public interface MultipGradeMapper {

    /**
     *
     * 批量查询综测成绩信息
     *
     * @param multipInfo 查询信息
     * @return 结果
     */
    public List<MultipGrade> selectInfo(MultipGrade multipInfo);

}
