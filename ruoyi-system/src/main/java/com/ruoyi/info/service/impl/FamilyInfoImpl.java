package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.FamilyInfo;
import com.ruoyi.info.mapper.FamilyInfoMapper;
import com.ruoyi.info.service.IFamilyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyInfoImpl implements IFamilyInfoService {

    @Autowired
    private FamilyInfoMapper familyInfoMapper;

    /**
     * 根据学生学号查询家庭成员信息
     *
     * @param studentId 学生学号
     * @return 家庭成员信息
     */
    @Override
    public List<FamilyInfo> selectFamilyById (String studentId){
        return familyInfoMapper.selectFamilyById(studentId);
    }

    /**
     * 修改家庭成员信息
     *
     * @param familyInfo 家庭成员信息
     * @return 结果
     */
    @Override
    public int updateFamily (FamilyInfo familyInfo){
        return familyInfoMapper.updateFamily(familyInfo);
    }

    /**
     * 新增家庭成员信息
     *
     * @param familyInfo 家庭成员信息
     * @return 结果
     */
    public int insertFamily(FamilyInfo familyInfo){
        int rows = familyInfoMapper.insertFamily(familyInfo);
        return rows;
    }
}
