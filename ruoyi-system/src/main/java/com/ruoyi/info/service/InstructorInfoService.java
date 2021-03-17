package com.ruoyi.info.service;

import com.ruoyi.info.domain.InstructorInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 辅导员信息 业务层
 *
 * @author akino
 */
public interface InstructorInfoService {

    /**
     * 查询辅导员信息
     *
     * @param instructorInfo 查询信息
     * @return 结果
     */
    public List<InstructorInfo> selectAllInstructor(InstructorInfo instructorInfo);

    /**
     * 添加辅导员基本信息
     *
     * @param instructorInfo 辅导员基本信息
     * @return 结果
     */
    public int insertInstructor(InstructorInfo instructorInfo);

    /**
     * 添加辅导员角色信息
     *
     * @param instructionId 辅导员工号
     * @return 结果
     */
    public int insertInstructorRole(String instructionId);

    /**
     * 删除辅导员基本信息
     *
     * @param instructorId 辅导员工号
     * @return 结果
     */
    public int deleteInstructor(String instructorId);

    /**
     * 删除辅导员角色信息
     *
     * @param userId 辅导员ID
     * @return 结果
     */
    public int deleteInstructorRole(Long userId);

    /**
     * 删除辅导员用户信息
     *
     * @param userId 辅导员ID
     * @return 结果
     */
    public int deleteInstructorUser(Long userId);

}
