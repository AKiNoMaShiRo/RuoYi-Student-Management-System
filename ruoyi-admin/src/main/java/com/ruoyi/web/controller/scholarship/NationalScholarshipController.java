package com.ruoyi.web.controller.scholarship;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.scholarship.domain.NationalScholarship;
import com.ruoyi.scholarship.service.INationalScholarshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("国家奖学金")
@RestController
@RequestMapping("/scholarship/national")
public class NationalScholarshipController extends BaseController {

    @Autowired
    private INationalScholarshipService nationalScholarshipService;

    @ApiOperation("查询国奖申请信息")
    @GetMapping("/query")
    public AjaxResult getNationalScholarship(NationalScholarship nationalScholarship){
        List<NationalScholarship> nationalScholarships = nationalScholarshipService.selectNationalApply(nationalScholarship);
        return AjaxResult.success(nationalScholarships);
    }

    @ApiOperation("查询已通过初审的国奖申请信息")
    @GetMapping("/queryRecheck")
    public AjaxResult getRecheckApply(NationalScholarship nationalScholarship){
        List<NationalScholarship> nationalScholarships = nationalScholarshipService.selectNationRecheck(nationalScholarship);
        return AjaxResult.success(nationalScholarships);
    }

    @ApiOperation("新增国奖申请")
    @PostMapping("/add")
    public AjaxResult addNationalScholarship(@Validated @RequestBody NationalScholarship nationalScholarship){
        return toAjax(nationalScholarshipService.insertNationalApply(nationalScholarship));
    }

    @ApiOperation("更新申请状态")
    @PreAuthorize("@ss.hasPermi('scholarship:national:approve')")
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@Validated @RequestBody NationalScholarship nationalScholarship){
        return toAjax(nationalScholarshipService.updateNationStatus(nationalScholarship));
    }

    @ApiOperation("删除申请")
    @PostMapping("/delete")
    public AjaxResult deleteStatus(@Validated @RequestBody NationalScholarship nationalScholarship){
        return toAjax(nationalScholarshipService.deleteNation(nationalScholarship));
    }

}
