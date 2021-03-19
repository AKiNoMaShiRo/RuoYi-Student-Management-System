package com.ruoyi.grade.service.Impl;

import com.ruoyi.grade.domain.LevelTest;
import com.ruoyi.grade.domain.LevelTestClass;
import com.ruoyi.grade.mapper.LevelTestMapper;
import com.ruoyi.grade.service.ILevelTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelTestServiceImpl implements ILevelTestService {

    @Autowired
    private LevelTestMapper levelTestMapper;

    /**
     * 根据学号查询学生成绩
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<LevelTest> selectByStuId(String studentId){
        return levelTestMapper.selectByStuId(studentId);
    }

    /**
     * 根据等级考试信息查询学生成绩，含班级信息
     *
     * @param levelTestClass 等级考试信息
     * @return 结果
     */
    public List<LevelTestClass> selectByTest(LevelTestClass levelTestClass){
        return levelTestMapper.selectByTest(levelTestClass);
    }

    /**
     * 根据等级考试信息查询学生成绩，含班级信息
     *
     * @param levelTestClass 等级考试信息
     * @return 结果
     */
    public double selectRateByTest(LevelTestClass levelTestClass){
        int sum = levelTestMapper.selectCountByTest(levelTestClass);
        levelTestClass.setIsPass("yes");
        int passNum = levelTestMapper.selectCountByTest(levelTestClass);
        return (double) passNum / (double) sum;
    }

}
