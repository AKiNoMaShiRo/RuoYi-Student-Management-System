package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.TempLeave;
import com.ruoyi.info.service.ITempLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/queryById")
    public TableDataInfo getTempLeave(String studentId){
        startPage();
        List<TempLeave> tempLeaves = tempLeaveService.selectLeaveById(studentId);
        return getDataTable(tempLeaves);
    }

    @ApiOperation("添加请假单信息")
    @PostMapping("/add")
    public AjaxResult addTempLeave(@Validated @RequestBody TempLeave tempLeave){
        return toAjax(tempLeaveService.insertTempLeave(tempLeave));
    }

    @ApiOperation("根据请假单编号删除请假信息")
    @DeleteMapping("/delete/{leaveId}")
    public AjaxResult deleteTempLeave(@PathVariable Long leaveId){
        return toAjax(tempLeaveService.deleteTempLeave(leaveId));
    }

    @ApiOperation("更新请假申请状态")
    @PreAuthorize("@ss.hasPermi('affair:templeave:approve')")
    @PostMapping("/setStatus")
    public AjaxResult setTempLeaveStatus(@Validated @RequestBody TempLeave tempLeave){
        return toAjax(tempLeaveService.updateStatus(tempLeave));
    }

    @ApiOperation("修改请假申请信息")
    @PostMapping("/edit")
    public AjaxResult setTempLeave(@Validated @RequestBody TempLeave tempLeave){
        return toAjax(tempLeaveService.updateTempLeave(tempLeave));
    }

}
