package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.ClassInfo;
import com.ruoyi.info.service.IClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("专业班级信息管理")
@RestController
@RequestMapping("/info/classInfo")
public class ClassInfoController extends BaseController {

    @Autowired
    private IClassInfoService classInfoService;

    @ApiOperation("查询所有班级信息")
    @PostMapping("/queryAll")
    public TableDataInfo queryAllClassInfo(@Validated @RequestBody ClassInfo classInfo){
        startPage();
        List<ClassInfo> classInfos = classInfoService.selectAllClassInfo(classInfo);
        return getDataTable(classInfos);
    }

    @ApiOperation("查询班级信息")
    @PostMapping("/query")
    public TableDataInfo queryClassInfo(@Validated @RequestBody ClassInfo classInfo){
        startPage();
        List<ClassInfo> classInfos = classInfoService.selectClassInfo(classInfo);
        return getDataTable(classInfos);
    }

    @ApiOperation("新增班级信息")
    @PostMapping("/add")
    public AjaxResult addClassInfo(@Validated @RequestBody ClassInfo classInfo){
        return toAjax(classInfoService.insertClassInfo(classInfo));
    }

    @ApiOperation("编辑班级信息")
    @PostMapping("/edit")
    public AjaxResult editClassInfo(@Validated @RequestBody ClassInfo classInfo){
        return toAjax(classInfoService.updateClassInfo(classInfo));
    }

    @ApiOperation("删除班级信息")
    @DeleteMapping("/delete/{classInfoId}")
    public AjaxResult deleteClassInfo(@PathVariable Long classInfoId){
        return toAjax(classInfoService.deleteClassInfo(classInfoId));
    }

}
