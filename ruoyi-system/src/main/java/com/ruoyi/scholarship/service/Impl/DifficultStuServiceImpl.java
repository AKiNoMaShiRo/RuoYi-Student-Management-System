package com.ruoyi.scholarship.service.Impl;

import com.ruoyi.scholarship.domain.DifficultStu;
import com.ruoyi.scholarship.mapper.DifficultStuMapper;
import com.ruoyi.scholarship.service.IDifficultStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DifficultStuServiceImpl implements IDifficultStuService {

    @Autowired
    private DifficultStuMapper difficultStuMapper;

    /**
     * 查询是否为困难生
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<DifficultStu> selectIsDiffStu(String studentId){
        return difficultStuMapper.selectIsDiffStu(studentId);
    }

}
