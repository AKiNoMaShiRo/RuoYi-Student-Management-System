package com.ruoyi.grade.service;

import com.ruoyi.grade.domain.MultipGrade;

import java.util.List;

public interface IMultipGradeService {

    /**
     *
     * 批量查询综测成绩信息
     *
     * @param multipInfo 查询信息
     * @return 结果
     */
    public List<MultipGrade> selectInfo(MultipGrade multipInfo);
}
