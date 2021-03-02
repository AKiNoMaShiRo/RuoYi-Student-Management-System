package com.ruoyi.web.controller.info;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.info.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info/stu")
public class StuInfoController extends BaseController {
//    @Autowired
    private IStuInfoService stuInfoServer;
}
