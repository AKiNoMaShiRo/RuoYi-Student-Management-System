package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.HeadmasterInfo;
import com.ruoyi.info.mapper.HeadmasterInfoMapper;
import com.ruoyi.info.service.IHeadmasterInfoService;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadmasterInfoServiceImpl implements IHeadmasterInfoService {

    @Autowired
    private HeadmasterInfoMapper infoMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public HeadmasterInfo selectHeadmaster(String headmasterId) {
        return infoMapper.selectHeadmaster(headmasterId);
    }

    /**
     * 批量查询班主任信息
     *
     * @param headmasterInfo 查询信息
     * @return 结果
     */
    public List<HeadmasterInfo> selectAllHeadmaster(HeadmasterInfo headmasterInfo){
        return infoMapper.selectAllHeadmaster(headmasterInfo);
    }

    /**
     * 添加班主任基本信息
     *
     * @param headmasterInfo 班主任基本信息
     * @return 结果
     */
    public int insertHeadmaster(HeadmasterInfo headmasterInfo){
        return infoMapper.insertHeadmaster(headmasterInfo);
    }

    /**
     * 添加班主任角色信息
     *
     * @param headmasterId 班主任用户ID
     * @return 结果
     */
    public int insertHeadmasterRole(String headmasterId){
        HeadmasterInfo res = infoMapper.selectHeadmaster(headmasterId);
        Long userId = res.getUserId();
        return infoMapper.insertHeadmasterRole(userId);
    }

    /**
     * 删除班主任基本信息
     *
     * @param headmasterId 班主任工号
     * @return 结果
     */
    public int deleteHeadmaster(String headmasterId){
        return infoMapper.deleteHeadmaster(headmasterId);
    }

    /**
     * 删除班主任角色信息
     *
     * @param userId 班主任ID
     * @return 结果
     */
    public int deleteHeadmasterRole(Long userId){
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }

    /**
     * 删除班主任用户信息
     *
     * @param userId 班主任ID
     * @return 结果
     */
    public int deleteHeadmasterUser(Long userId){
        return userMapper.deleteUserById(userId);
    }

}
