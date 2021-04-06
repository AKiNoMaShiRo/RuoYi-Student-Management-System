package com.ruoyi.web.controller.notify;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.notify.domain.NotifyUserInfo;
import com.ruoyi.notify.service.INotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("消息通知")
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private INotifyService notifyService;

    @ApiOperation("查询通知消息")
    @PostMapping("/query")
    public AjaxResult getNotifyInfo(@Validated @RequestBody NotifyUserInfo notifyUserInfo){
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put(AjaxResult.DATA_TAG, notifyService.selectNotify(notifyUserInfo));
        return ajaxResult;
    }

}
