package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.FamilyInfo;

import java.util.List;

public interface FamilyInfoMapper {
    /**
     * 根据学生学号查询家庭成员信息
     *
     * @param studentId 学生学号
     * @return 家庭成员信息
     */
    public List<FamilyInfo> selectFamilyById (String studentId);

    /**
     * 修改学生信息
     *
     * @param familyInfo 学生信息
     * @return 结果
     */
    public int updateFamily(FamilyInfo familyInfo);

    /**
     * 新增家庭成员信息
     *
     * @param familyInfo 家庭成员信息
     * @return 结果
     */
    public int insertFamily(FamilyInfo familyInfo);
}
