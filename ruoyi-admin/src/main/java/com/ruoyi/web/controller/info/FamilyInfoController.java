package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.info.domain.FamilyInfo;
import com.ruoyi.info.service.IFamilyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家庭成员信息
 *
 * @author akino
 */
@Api("家庭成员信息管理")
@RestController
@RequestMapping("/info/family")
public class FamilyInfoController extends BaseController {

    @Autowired
    private IFamilyInfoService familyInfoService;

    @ApiOperation("根据学号查找家庭成员信息")
    @GetMapping(value = { "/list/queryById/{studentId}"})
    public AjaxResult getInfo(@PathVariable(value = "studentId")String studentId){
        List<FamilyInfo> familyInfos = familyInfoService.selectFamilyById(studentId);
        return AjaxResult.success(familyInfos);
    }

    @ApiOperation("修改家庭成员信息")
    @PostMapping("/edit")
    public AjaxResult editInfo(@Validated @RequestBody FamilyInfo familyInfo){
        return toAjax(familyInfoService.updateFamily(familyInfo));
    }

    @ApiOperation("新增家庭成员信息")
    @PostMapping("/add")
    public AjaxResult addInfo(@Validated @RequestBody FamilyInfo familyInfo){
        return toAjax(familyInfoService.insertFamily(familyInfo));
    }

    @ApiOperation("删除家庭成员信息")
    @DeleteMapping("/delete/{memberId}")
    public AjaxResult deleteInfo(@PathVariable Long memberId){
        return toAjax(familyInfoService.deleteFamily(memberId));
    };

}
