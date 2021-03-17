package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.InstructorInfo;

import java.util.List;

public interface InstructorInfoMapper {

    /**
     * 查询辅导员信息
     *
     * @param instructorId 辅导员工号
     * @return 结果
     */
    public InstructorInfo selectInstructor(String instructorId);

    /**
     * 批量查询辅导员信息
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
     * @param userId 辅导员用户ID
     * @return 结果
     */
    public int insertInstructorRole(Long userId);

    /**
     * 删除辅导员基本信息
     *
     * @param instructorId 辅导员工号
     * @return 结果
     */
    public int deleteInstructor(String instructorId);

}
