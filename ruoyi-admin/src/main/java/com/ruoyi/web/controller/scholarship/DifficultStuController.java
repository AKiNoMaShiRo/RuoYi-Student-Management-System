package com.ruoyi.web.controller.scholarship;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.scholarship.domain.DifficultStu;
import com.ruoyi.scholarship.service.IDifficultStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.tool.Diff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@Api("困难生信息查询")
@RestController
@RequestMapping("/difficult/student")
public class DifficultStuController extends BaseController {

    @Autowired
    private IDifficultStuService difficultStuService;

    @ApiOperation("查询是否为困难生")
    @GetMapping("/query")
    public AjaxResult queryIsDifficult(DifficultStu stu){
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put(AjaxResult.DATA_TAG, difficultStuService.selectIsDiffStu(stu.getStudentId()));
        return ajaxResult;
    }

}
