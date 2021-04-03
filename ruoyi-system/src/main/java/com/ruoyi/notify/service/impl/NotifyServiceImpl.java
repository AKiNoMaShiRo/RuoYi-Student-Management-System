package com.ruoyi.notify.service.impl;

import com.ruoyi.notify.domain.Notify;
import com.ruoyi.notify.domain.NotifyUserInfo;
import com.ruoyi.notify.mapper.NotifyMapper;
import com.ruoyi.notify.service.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知消息 业务层处理
 *
 * @author akino
 */
@Service
public class NotifyServiceImpl implements INotifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    /**
     * 查询待审批事项通知消息
     *
     * @param notifyUserInfo 用户信息
     * @return 结果
     */
    public List<Notify> selectNotify(NotifyUserInfo notifyUserInfo){
        Notify notifyTL = new Notify("临时请假申请",notifyMapper.selectTLNum(notifyUserInfo));
        Notify notifyHG = new Notify("节假日去向报备", notifyMapper.selectHGNum(notifyUserInfo));
        Notify notifyNB = new Notify("外宿申请", notifyMapper.selectNBNum(notifyUserInfo));
        List<Notify> notifies = new ArrayList<>();
        notifies.add(notifyTL);
        notifies.add(notifyHG);
        notifies.add(notifyNB);
        return notifies;
    }
}
