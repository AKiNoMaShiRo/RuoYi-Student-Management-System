package com.ruoyi.grade.service.Impl;

import com.ruoyi.grade.domain.CourseGradeClass;
import com.ruoyi.grade.mapper.CourseGradeMapper;
import com.ruoyi.grade.service.ICourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGradeServiceImpl implements ICourseGradeService {

    @Autowired
    private CourseGradeMapper courseGradeMapper;

//    /**
//     * 根据学号查询学生成绩
//     *
//     * @param studentId 学号
//     * @return 结果
//     */
//    public List<CourseGradeClass> selectByStuId(String studentId){
//        return courseGradeMapper.selectByStuId(studentId);
//    }

    /**
     * 查询普通课程成绩，可查多个学生
     *
     * @param courseGradeClass 学号
     * @return 结果
     */
    public List<CourseGradeClass> selectByGrade(CourseGradeClass courseGradeClass){
        return courseGradeMapper.selectByGradeId(courseGradeClass);
    }

}
