package com.ruoyi.web.controller.grade;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.grade.domain.CourseGradeClass;
import com.ruoyi.grade.domain.GainedPoint;
import com.ruoyi.grade.service.ICourseGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("普通课程成绩")
@RestController
@RequestMapping("/course/grade")
public class CourseGradeController extends BaseController {

    @Autowired
    private ICourseGradeService courseGradeService;

    @ApiOperation("批量查询课程成绩")
    @GetMapping("/queryAll")
    public TableDataInfo getAllCourseGrade(CourseGradeClass courseGradeClass){
        startPage();
        List<CourseGradeClass> courseGradeClasses = courseGradeService.selectByGrade(courseGradeClass);
        return getDataTable(courseGradeClasses);
    }

    @ApiOperation("根据学号查询学生学分")
    @PostMapping("/queryPoint")
    public AjaxResult getStuPoint(@Validated @RequestBody CourseGradeClass courseGradeClass){
        AjaxResult ajaxResult = AjaxResult.success();
        if (courseGradeClass.getStudentId() != null){
            ajaxResult.put(AjaxResult.DATA_TAG, courseGradeService.selectPointByStuId(courseGradeClass));
        }
        return ajaxResult;
    }

    @ApiOperation("查询课程名称")
    @GetMapping("/course/type")
    public AjaxResult queryCourseName(GainedPoint gainedPoint){
        List<CourseGradeClass> points = courseGradeService.selectCourseName(gainedPoint.getCourseType());
        return AjaxResult.success(points);
    }

    @ApiOperation("查询课程平均分")
    @GetMapping("/course/avg")
    public AjaxResult queryCourseAvg(CourseGradeClass courseGradeClass){
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put(AjaxResult.DATA_TAG, courseGradeService.selectCourseAvgByClass(courseGradeClass));
        return ajaxResult;
    }

}
