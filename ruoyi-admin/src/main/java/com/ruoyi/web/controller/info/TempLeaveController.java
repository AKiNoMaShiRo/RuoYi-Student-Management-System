package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.info.domain.TempLeave;
import com.ruoyi.info.service.ITempLeaveService;
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
@Api("临时请假申请管理")
@RestController
@RequestMapping("/temp/leave")
public class TempLeaveController extends BaseController {

    @Autowired
    private ITempLeaveService tempLeaveService;

    @ApiOperation("根据学号查询请假单信息")
    @GetMapping("/queryById/{studentId}")
    public AjaxResult getTempLeave(@PathVariable String studentId){
        List<TempLeave> tempLeaves = tempLeaveService.selectLeaveById(studentId);
        return AjaxResult.success(tempLeaves);
    }

    @ApiOperation("添加请假单信息")
    @PostMapping("/add")
    public AjaxResult addTempLeave(@Validated @RequestBody TempLeave tempLeave){
        return toAjax(tempLeaveService.insertTempLeave(tempLeave));
    }

}
