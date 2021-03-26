package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.MasterInfo;
import com.ruoyi.info.service.IMasterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("副书记账号管理")
@RestController
@RequestMapping("/info/master")
public class MasterInfoController extends BaseController {

    @Autowired
    private IMasterInfoService masterInfoService;

    @ApiOperation("查询副书记信息")
    @GetMapping("/queryAll")
    public AjaxResult getMasterAllInfo(MasterInfo masterInfo){
//        startPage();
        List<MasterInfo> masterInfos = masterInfoService.selectMaster(masterInfo);
        return AjaxResult.success(masterInfos);
    }

    @ApiOperation("删除副书记信息")
    @DeleteMapping("/delete/{userId}")
    public AjaxResult deleteMasterInfo(@PathVariable Long userId){
        return toAjax(masterInfoService.deleteMasterInfo(userId));
    }

}
