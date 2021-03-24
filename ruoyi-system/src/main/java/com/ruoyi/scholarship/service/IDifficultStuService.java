package com.ruoyi.scholarship.service;

import com.ruoyi.scholarship.domain.DifficultStu;

import java.util.List;

public interface IDifficultStuService {

    /**
     * 查询是否为困难生
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<DifficultStu> selectIsDiffStu(String studentId);

}
