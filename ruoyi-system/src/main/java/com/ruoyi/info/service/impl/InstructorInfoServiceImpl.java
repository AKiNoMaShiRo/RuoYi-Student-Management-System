package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.InstructorInfo;
import com.ruoyi.info.mapper.InstructorInfoMapper;
import com.ruoyi.info.service.InstructorInfoService;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorInfoServiceImpl implements InstructorInfoService {

    @Autowired
    private InstructorInfoMapper infoMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询辅导员信息
     *
     * @param instructorInfo 查询信息
     * @return 结果
     */
    public List<InstructorInfo> selectAllInstructor(InstructorInfo instructorInfo){
        return infoMapper.selectAllInstructor(instructorInfo);
    }

    /**
     * 添加辅导员基本信息
     *
     * @param instructorInfo 辅导员基本信息
     * @return 结果
     */
    public int insertInstructor(InstructorInfo instructorInfo){
        return infoMapper.insertInstructor(instructorInfo);
    }

    /**
     * 添加辅导员角色信息
     *
     * @param instructionId 辅导员角色信息
     * @return 结果
     */
    public int insertInstructorRole(String instructionId){
        InstructorInfo res = infoMapper.selectInstructor(instructionId);
        Long userId = res.getUserId();
        return infoMapper.insertInstructorRole(userId);
    }

    /**
     * 删除辅导员基本信息
     *
     * @param instructorId 辅导员工号
     * @return 结果
     */
    public int deleteInstructor(String instructorId){
        return infoMapper.deleteInstructor(instructorId);
    }

    /**
     * 删除辅导员角色信息
     *
     * @param userId 辅导员ID
     * @return 结果
     */
    public int deleteInstructorRole(Long userId){
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }

    /**
     * 删除辅导员用户信息
     *
     * @param userId 辅导员ID
     * @return 结果
     */
    public int deleteInstructorUser(Long userId){
        return userMapper.deleteUserById(userId);
    }

}
