package com.ruoyi.notify.mapper;

import com.ruoyi.notify.domain.NotifyUserInfo;

public interface NotifyMapper {

    /**
     * 查询待审批的临时请假申请
     *
     * @param notifyUserInfo 用户信息
     * @return 结果
     */
    public int selectTLNum(NotifyUserInfo notifyUserInfo);

    /**
     * 查询待审批的节假期去向报备
     *
     * @param notifyUserInfo 用户信息
     * @return 结果
     */
    public int selectHGNum(NotifyUserInfo notifyUserInfo);

    /**
     * 查询待审批的外宿申请
     *
     * @param notifyUserInfo 用户信息
     * @return 结果
     */
    public int selectNBNum(NotifyUserInfo notifyUserInfo);

}
