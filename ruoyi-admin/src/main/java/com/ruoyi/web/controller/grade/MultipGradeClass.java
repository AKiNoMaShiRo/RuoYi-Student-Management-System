package com.ruoyi.web.controller.grade;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.grade.domain.MultipGrade;
import com.ruoyi.grade.service.IMultipGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("综合测评信息")
@RestController
@RequestMapping("/multip/grade")
public class MultipGradeClass extends BaseController {

    @Autowired
    private IMultipGradeService multipGradeService;

    @ApiOperation("批量查询综测信息")
    @PostMapping("/queryAll")
    public TableDataInfo getAllInfo(@Validated @RequestBody MultipGrade multipGrade){
        startPage();
        List<MultipGrade> grades = multipGradeService.selectInfo(multipGrade);
        return getDataTable(grades);
    }

}
