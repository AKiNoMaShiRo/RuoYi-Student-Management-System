package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.InstructorInfo;
import com.ruoyi.info.service.InstructorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("辅导员信息管理")
@RestController
@RequestMapping("/info/instructor")
public class InstructorInfoController extends BaseController {

    @Autowired
    private InstructorInfoService instructorInfoService;

    @ApiOperation("查询辅导员信息")
    @GetMapping("/queryAll")
    public TableDataInfo getAllInstrouctorInfo(InstructorInfo instructorInfo){
        startPage();
        List<InstructorInfo> instructorInfos = instructorInfoService.selectAllInstructor(instructorInfo);
        return getDataTable(instructorInfos);
    }

    @ApiOperation("添加辅导员基本信息")
    @PostMapping("/add")
    public AjaxResult addInstructorInfo(@Validated @RequestBody InstructorInfo instructorInfo){
        return toAjax(instructorInfoService.insertInstructor(instructorInfo));
    }

    @ApiOperation("添加辅导员角色信息")
    @PostMapping("/add/role")
    public AjaxResult addInstructorRole(@Validated @RequestBody InstructorInfo info){
        return toAjax(instructorInfoService.insertInstructorRole(info.getInstructorId()));
    }

    @ApiOperation("删除辅导员基本信息")
    @DeleteMapping("/delete/{instructorId}")
    public AjaxResult deleteInstructorInfo(@PathVariable String instructorId){
        return toAjax(instructorInfoService.deleteInstructor(instructorId));
    }

    @ApiOperation("删除辅导员角色信息")
    @DeleteMapping("/deleteRole/{userId}")
    public AjaxResult deleteInstructorRoleInfo(@PathVariable Long userId){
        return toAjax(instructorInfoService.deleteInstructorRole(userId));
    }

    @ApiOperation("删除辅导员用户信息")
    @DeleteMapping("/deleteUser/{userId}")
    public AjaxResult deleteInstructorUserInfo(@PathVariable Long userId){
        return toAjax(instructorInfoService.deleteInstructorUser(userId));
    }

}
