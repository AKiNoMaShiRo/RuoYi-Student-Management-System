package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.info.domain.HeadmasterInfo;
import com.ruoyi.info.domain.InstructorInfo;
import com.ruoyi.info.service.IHeadmasterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("班主任信息")
@RestController
@RequestMapping("/info/headmaster")
public class HeadmasterController extends BaseController {

    @Autowired
    private IHeadmasterInfoService infoService;

    @ApiOperation("查询班主任信息")
    @GetMapping("/queryAll")
    public TableDataInfo getAllHeadmasterInfo(HeadmasterInfo headmasterInfo){
        startPage();
        List<HeadmasterInfo> headmasterInfos = infoService.selectAllHeadmaster(headmasterInfo);
        return getDataTable(headmasterInfos);
    }

    @ApiOperation("添加班主任基本信息")
    @PostMapping("/add")
    public AjaxResult addHeadmasterInfo(@Validated @RequestBody HeadmasterInfo headmasterInfo){
        return toAjax(infoService.insertHeadmaster(headmasterInfo));
    }

    @ApiOperation("添加班主任角色信息")
    @PostMapping("/add/role")
    public AjaxResult addHeadmasterRole(@Validated @RequestBody HeadmasterInfo info){
        return toAjax(infoService.insertHeadmasterRole(info.getHeadmasterId()));
    }

    @ApiOperation("删除班主任基本信息")
    @DeleteMapping("/delete/{headmasterId}")
    public AjaxResult deleteHeadmasterInfo(@PathVariable String headmasterId){
        return toAjax(infoService.deleteHeadmaster(headmasterId));
    }

    @ApiOperation("删除班主任角色信息")
    @DeleteMapping("/deleteRole/{userId}")
    public AjaxResult deleteHeadmasterRoleInfo(@PathVariable Long userId){
        return toAjax(infoService.deleteHeadmasterRole(userId));
    }

    @ApiOperation("删除班主任用户信息")
    @DeleteMapping("/deleteUser/{userId}")
    public AjaxResult deleteHeadmasterUserInfo(@PathVariable Long userId){
        return toAjax(infoService.deleteHeadmasterUser(userId));
    }

}
