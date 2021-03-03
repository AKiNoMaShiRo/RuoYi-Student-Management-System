package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.info.service.IStuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = { "/{studentId}"})
    public AjaxResult getInfo(@PathVariable(value = "studentId")String studentId){
        AjaxResult ajaxResult = AjaxResult.success();
        if(StringUtils.isNotNull(studentId)){
            ajaxResult.put(AjaxResult.DATA_TAG, stuInfoService.selectStuById(studentId));
        }
        return ajaxResult;
    }
}
