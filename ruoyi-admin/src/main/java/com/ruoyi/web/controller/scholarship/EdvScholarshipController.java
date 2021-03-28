package com.ruoyi.web.controller.scholarship;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.scholarship.domain.EdvScholarship;
import com.ruoyi.scholarship.service.IEdvScholarshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("国励奖学金")
@RestController
@RequestMapping("/scholarship/endeavor")
public class EdvScholarshipController extends BaseController {

    @Autowired
    private IEdvScholarshipService edvScholarshipService;

    @ApiOperation("查询国励申请信息")
    @GetMapping("/query")
    public AjaxResult getEdvScholarship(EdvScholarship edvScholarship){
        List<EdvScholarship> edvScholarships = edvScholarshipService.selectEdvApply(edvScholarship);
        return AjaxResult.success(edvScholarships);
    }

    @ApiOperation("新增国励申请")
    @PostMapping("/add")
    public AjaxResult addEdvScholarship(@Validated @RequestBody EdvScholarship edvScholarship){
        return toAjax(edvScholarshipService.insertEdvApply(edvScholarship));
    }

    @ApiOperation("更新申请状态")
    @PreAuthorize("@ss.hasPermi('scholarship:endeavor:approve')")
    @PostMapping("/updateStatus")
    public AjaxResult updateEdvScholarshipStatus(@Validated @RequestBody EdvScholarship edvScholarship){
        return toAjax(edvScholarshipService.updateEdvStatus(edvScholarship));
    }

    @ApiOperation("删除国励申请")
    @PostMapping("/delete")
    public AjaxResult deleteEdvScholarship(@Validated @RequestBody EdvScholarship edvScholarship){
        return toAjax(edvScholarshipService.deleteEdvStatus(edvScholarship));
    }
}
