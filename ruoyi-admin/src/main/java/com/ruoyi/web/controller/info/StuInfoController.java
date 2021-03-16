package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.info.domain.StuBaseInfo;
import com.ruoyi.info.domain.StuInfo;
import com.ruoyi.info.domain.StuUserInfo;
import com.ruoyi.info.service.IStuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生信息
 *
 * @author akino
 */
@Api("学生信息管理")
@RestController
@RequestMapping("/info/stu")
public class StuInfoController extends BaseController {

    @Autowired
    private IStuInfoService stuInfoService;

    @ApiOperation("根据学号查询学生信息")
    @GetMapping(value = { "/queryById/{studentId}"})
    public AjaxResult getInfo(@PathVariable(value = "studentId")String studentId){
        AjaxResult ajaxResult = AjaxResult.success();
        if(StringUtils.isNotNull(studentId)){
            ajaxResult.put(AjaxResult.DATA_TAG, stuInfoService.selectStuById(studentId));
        }
        return ajaxResult;
    }

    @ApiOperation("批量查询学生信息")
    @GetMapping(value = { "/queryAll"})
    public TableDataInfo getAllInfo(StuBaseInfo stuBaseInfo){
        startPage();
        List<StuBaseInfo> stuBaseInfos = stuInfoService.selectAllStuBase(stuBaseInfo);
        return getDataTable(stuBaseInfos);
    }

    @ApiOperation("添加学生账号")
    @PostMapping(value = { "/add"})
    public AjaxResult addStuInfo(@Validated @RequestBody StuUserInfo stuUserInfo){
        return toAjax(stuInfoService.insertStuBaseInfo(stuUserInfo));
    }

    @ApiOperation("修改学生信息")
    @PostMapping("/edit")
    public AjaxResult editInfo(@Validated @RequestBody StuInfo stuInfo){
        return toAjax(stuInfoService.updateStu(stuInfo));
    }

    @ApiOperation("根据学号查询学生基本信息")
    @GetMapping(value = { "/queryBaseById/{studentId}"})
    public AjaxResult getBaseInfo(@PathVariable(value = "studentId")String studentId){
        AjaxResult ajaxResult = AjaxResult.success();
        if(StringUtils.isNotNull(studentId)){
            ajaxResult.put(AjaxResult.DATA_TAG, stuInfoService.selectStuBaseById(studentId));
        }
        return ajaxResult;
    }
}
