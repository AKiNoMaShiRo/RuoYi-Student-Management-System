package com.ruoyi.web.controller.grade;

import com.ruoyi.affair.domain.Holiday;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.grade.domain.LevelTest;
import com.ruoyi.grade.domain.LevelTestClass;
import com.ruoyi.grade.service.ILevelTestService;
import com.ruoyi.info.service.IClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 等级考试信息
 *
 * @author akino
 */
@Api("等级考试信息")
@RestController
@RequestMapping("/level/test")
public class LevelTestController extends BaseController {

    @Autowired
    private ILevelTestService levelTestService;

    @ApiOperation("根据学号查询学生等级考试成绩")
    @GetMapping("/queryById")
    public TableDataInfo getLevelTest(String studentId) {
        startPage();
        List<LevelTest> levelTests = levelTestService.selectByStuId(studentId);
        return getDataTable(levelTests);
    }

    @ApiOperation("根据等级考试信息查询学生成绩，含班级信息")
    @GetMapping("/queryByLT")
    public TableDataInfo getLevelTestClass(LevelTestClass levelTestClass) {
        startPage();
        List<LevelTestClass> levelTests = levelTestService.selectByTest(levelTestClass);
        return getDataTable(levelTests);
    }

//    @ApiOperation("根据等级考试信息查询通过率")
//    @GetMapping("/queryRate")
//    public AjaxResult getLevelTestPassRate(LevelTestClass levelTestClass) {
//        double res = levelTestService.selectRateByTest(levelTestClass);
//        AjaxResult ajaxResult = AjaxResult.success();
//        ajaxResult.put(AjaxResult.DATA_TAG, res);
//        return ajaxResult;
//    }

    @ApiOperation("根据等级考试信息查询通过率")
    @PostMapping("/queryRate")
    public AjaxResult getLevelTestPassRate(@Validated @RequestBody LevelTestClass[] levelTestClass) {
        int len = levelTestClass.length;
        double[] res = new double[len];
        for(int i = 0; i < levelTestClass.length ; i ++){
            res[i] = levelTestService.selectRateByTest(levelTestClass[i]);
        }
//        double res = levelTestService.selectRateByTest(levelTestClass);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put(AjaxResult.DATA_TAG, res);
        return ajaxResult;
    }

}
