package com.ruoyi.info.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuBaseInfo;
import com.ruoyi.info.domain.StuInfo;
import com.ruoyi.info.domain.StuUserInfo;
import com.ruoyi.info.mapper.StuInfoMapper;
import com.ruoyi.info.service.IStuInfoService;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息 业务层处理
 *
 * @author akino
 */
@Service
public class StuInfoServiceImpl implements IStuInfoService {

    @Autowired
    private StuInfoMapper infoMapper;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    @Override
    public StuInfo selectStuById(String studentId){
        return infoMapper.selectStuById(studentId);
    }

    /**
     * 修改学生信息
     *
     * @param stuInfo 学生信息
     * @return 结果
     */
    @Override
    public int updateStu(StuInfo stuInfo){
        return infoMapper.updateStu(stuInfo);
    }


//    /**
//     * 修改学生基本信息
//     *
//     * @param stuBaseInfo 学生信息
//     * @return 结果
//     */
//    public int updateStuBase(StuBaseInfo stuBaseInfo){
//        return infoMapper.updateStuBaseInfo(stuBaseInfo);
//    }

    /**
     * 根据学生学号查询学生基本信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    @Override
    public StuBaseInfo selectStuBaseById(String studentId){
        return infoMapper.selectStuBaseById(studentId);
    }

    /**
     * 批量查询学生基本信息
     *
     * @param stuBaseInfo 入参
     * @return 学生信息
     */
    public List<StuBaseInfo> selectAllStuBase(StuBaseInfo stuBaseInfo){
        return infoMapper.selectAllStuBase(stuBaseInfo);
    }

    /**
     * 批量查询学生信息
     *
     * @param stuInfo 入参
     * @return 学生信息
     */
    public List<StuBaseInfo> selectAllStu(StuInfo stuInfo){
        return infoMapper.selectAllStu(stuInfo);
    }

    /**
     * 添加学生基本信息
     *
     * @param baseInfo 入参
     * @return 结果
     */
    public int insertStuBaseInfo(StuBaseInfo baseInfo){
        return infoMapper.insertStuBaseInfo(baseInfo);
    }

    /**
     * 添加学生角色信息
     *
     * @param studentId 学生学号
     * @return 结果
     */
    public int insertStuRoleInfo(String studentId){
        StuBaseInfo stuBaseInfo = infoMapper.selectStuUserId(studentId);
        Long userId = stuBaseInfo.getUserId();
        return infoMapper.insertStuRoleInfo(userId);
    }

    /**
     * 删除学生基本信息
     *
     * @param studentId 学生学号
     * @return 结果
     */
    public int deleteStuBaseInfo(String studentId){
        return infoMapper.deleteStuInfo(studentId);
    }

    /**
     * 删除学生角色信息
     *
     * @param userId 学生用户ID
     * @return 结果
     */
    public int deleteStuRoleInfo(Long userId){
        return infoMapper.deleteStuRoleInfo(userId);
    }

    /**
     * 删除学生用户信息
     *
     * @param userId 学生用户ID
     * @return 结果
     */
    public int deleteStuUserInfo(Long userId){
        return userMapper.deleteUserById(userId);
    }

}
