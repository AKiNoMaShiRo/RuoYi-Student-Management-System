package com.ruoyi.info.service;

import com.ruoyi.info.domain.MasterInfo;

import java.util.List;

public interface IMasterInfoService {

    /**
     * 查询副书记信息
     *
     * @param masterInfo 副书记信息
     * @return 结果
     */
    public List<MasterInfo> selectMaster(MasterInfo masterInfo);

    /**
     * 删除副书记信息
     *
     * @param userId 副书记用户ID
     * @return 结果
     */
    public int deleteMasterInfo(Long userId);
}
