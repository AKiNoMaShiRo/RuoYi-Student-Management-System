package com.ruoyi.notify.service;

import com.ruoyi.notify.domain.Notify;
import com.ruoyi.notify.domain.NotifyUserInfo;

import java.util.List;

/**
 * 通知消息 业务层处理
 *
 * @author akino
 */
public interface INotifyService {

    /**
     * 查询待审批事项通知消息
     *
     * @param notifyUserInfo 用户信息
     * @return 结果
     */
    public List<Notify> selectNotify(NotifyUserInfo notifyUserInfo);
}
