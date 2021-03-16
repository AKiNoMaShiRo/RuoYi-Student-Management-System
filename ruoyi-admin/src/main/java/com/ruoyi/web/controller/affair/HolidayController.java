package com.ruoyi.web.controller.affair;

import com.ruoyi.affair.domain.Holiday;
import com.ruoyi.affair.service.IHolidayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 节假日去向
 *
 * @author akino
 */
@Api("节假日去向")
@RestController
@RequestMapping("/holiday")
public class HolidayController extends BaseController {

    @Autowired
    private IHolidayService holidayService;

    @ApiOperation("根据学号查询节假日去向报备")
    @GetMapping("/queryById")
    public TableDataInfo getHoliday(String studentId) {
        startPage();
        List<Holiday> holidays = holidayService.selectHolidayById(studentId);
        return getDataTable(holidays);
    }

    @ApiOperation("根据辅导员查询节假日去向报备")
    @GetMapping("/instructor/queryById")
    public TableDataInfo getInsHoliday(String instructorId) {
        startPage();
        List<Holiday> holidays = holidayService.selectHolidayByInsId(instructorId);
        return getDataTable(holidays);
    }

    @ApiOperation("新增节假日去向报备信息")
    @PostMapping("/add")
    public AjaxResult addHoliday(@Validated @RequestBody Holiday holiday){
        return toAjax(holidayService.addHoliday(holiday));
    }

    @ApiOperation("修改节假日去向报备信息状态")
    @PreAuthorize("@ss.hasPermi('affair:holiday:approve')")
    @PostMapping("/setStatus")
    public AjaxResult setHolidayStatus(@Validated @RequestBody Holiday holiday){
        return toAjax(holidayService.updateHolidayStatus(holiday));
    }

    @ApiOperation("修改节假日去向报备信息")
    @PostMapping("/edit")
    public AjaxResult setHoliday(@Validated @RequestBody Holiday holiday){
        return toAjax(holidayService.updateHoliday(holiday));
    }

    @ApiOperation("删除节假日去向报备信息")
    @DeleteMapping("/delete/{goId}")
    public AjaxResult deletdHoliday(@PathVariable Long goId){
        return toAjax(holidayService.deleteHoliday(goId));
    }

}
