package com.ruoyi.grade.mapper;

import com.ruoyi.grade.domain.LevelTest;
import com.ruoyi.grade.domain.LevelTestClass;

import java.util.List;

public interface LevelTestMapper {

    /**
     * 根据学号查询学生成绩
     *
     * @param studentId 学号
     * @return 结果
     */
    public List<LevelTest> selectByStuId(String studentId);

    /**
     * 根据等级考试信息查询学生成绩，含班级信息
     *
     * @param levelTestClass 等级考试信息
     * @return 结果
     */
    public List<LevelTestClass> selectByTest(LevelTestClass levelTestClass);

    /**
     * 根据等级考试信息查询总条数
     *
     * @param levelTestClass 等级考试信息
     * @return 结果
     */
    public int selectCountByTest(LevelTestClass levelTestClass);

}
