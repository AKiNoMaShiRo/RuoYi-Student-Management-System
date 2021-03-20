package com.ruoyi.web.controller.grade;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.grade.domain.CourseGradeClass;
import com.ruoyi.grade.service.ICourseGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
