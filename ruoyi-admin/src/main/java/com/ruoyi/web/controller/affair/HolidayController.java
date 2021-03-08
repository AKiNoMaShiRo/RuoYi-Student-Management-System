package com.ruoyi.web.controller.affair;

import com.ruoyi.affair.domain.Holiday;
import com.ruoyi.affair.service.IHolidayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("节假日去向")
@RestController
@RequestMapping("/holiday")
public class HolidayController extends BaseController {

    @Autowired
    private IHolidayService holidayService;

    @ApiOperation("根据学号查询节假日去向申请")
    @GetMapping("/queryById")
    public TableDataInfo getHoliday(String studentId) {
        startPage();
        List<Holiday> holidays = holidayService.selectHolidayById(studentId);
        return getDataTable(holidays);
    }
}
