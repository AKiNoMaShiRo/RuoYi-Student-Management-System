package com.ruoyi.grade.service.Impl;

import com.ruoyi.grade.domain.CourseGradeClass;
import com.ruoyi.grade.domain.GainedPoint;
import com.ruoyi.grade.mapper.CourseGradeMapper;
import com.ruoyi.grade.service.ICourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 根据学号、课程类别查询学生已获学分
     *
     * @param courseGradeClass 学号
     * @return 结果
     */
    public List<GainedPoint> selectPointByStuId(CourseGradeClass courseGradeClass){
        List<GainedPoint> points = new ArrayList<>();
        List<String> courseType = new ArrayList<>();
        courseType.add("学科(必)");
        courseType.add("专业(必)");
        courseType.add("专业(选)");
        courseType.add("实践(必)");
        courseType.add("实践(选)");
        courseType.add("公共(必)");
        courseType.add("方向(选)");
        courseType.add("通修课");
        for(int i=0 ; i<courseType.size() ; i++){
            GainedPoint temp = new GainedPoint();
            courseGradeClass.setCourseType(courseType.get(i));
            temp.setCourseType(courseType.get(i));
            if (courseGradeMapper.selectPassPoint(courseGradeClass).equals("")) {
                temp.setGainedPoint("0");
            } else {
                temp.setGainedPoint(courseGradeMapper.selectPassPoint(courseGradeClass));
            }
            points.set(i, temp);
        }
        return points;
    }

    /**
     * 查询有哪些课程名称
     *
     * @param
     * @return 课程名称
     */
    public List<CourseGradeClass> selectCourseName(String courseName){
        return courseGradeMapper.selectCourseName(courseName);
    }

    /**
     * 查询某一个班级某一课程的平均分
     *
     * @param courseGradeClass 课程名称和班级信息
     * @return 平均分
     */
    public String selectCourseAvgByClass(CourseGradeClass courseGradeClass){
        return courseGradeMapper.selectCourseAvgByClass(courseGradeClass);
    }

}
