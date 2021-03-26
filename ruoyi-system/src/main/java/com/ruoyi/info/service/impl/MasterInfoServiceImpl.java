package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.MasterInfo;
import com.ruoyi.info.mapper.MasterInfoMapper;
import com.ruoyi.info.service.IMasterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterInfoServiceImpl implements IMasterInfoService {

    @Autowired
    private MasterInfoMapper masterInfoMapper;

    /**
     * 查询副书记信息
     *
     * @param masterInfo 副书记信息
     * @return 结果
     */
    public List<MasterInfo> selectMaster(MasterInfo masterInfo){
        return masterInfoMapper.selectMasterInfo(masterInfo);
    }

    /**
     * 删除副书记信息
     *
     * @param userId 副书记用户ID
     * @return 结果
     */
    public int deleteMasterInfo(Long userId){
        return masterInfoMapper.deleteMasterInfo(userId);
    }
}
