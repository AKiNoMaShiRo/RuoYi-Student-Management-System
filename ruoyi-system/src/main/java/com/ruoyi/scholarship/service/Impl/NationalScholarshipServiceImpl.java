package com.ruoyi.scholarship.service.Impl;

import com.ruoyi.scholarship.domain.NationalScholarship;
import com.ruoyi.scholarship.mapper.NationalScholarshipMapper;
import com.ruoyi.scholarship.service.INationalScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalScholarshipServiceImpl implements INationalScholarshipService {

    @Autowired
    private NationalScholarshipMapper nationalScholarshipMapper;

    /**
     * 查询国奖申请
     *
     * @param nationalScholarship 查询信息
     * @return 结果
     */
    public List<NationalScholarship> selectNationalApply(NationalScholarship nationalScholarship){
        return nationalScholarshipMapper.selectNationalApply(nationalScholarship);
    }

    /**
     * 新增国奖申请
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int insertNationalApply(NationalScholarship nationalScholarship){
        return nationalScholarshipMapper.insertNationalApply(nationalScholarship);
    }

    /**
     * 更新国奖申请状态
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int updateNationStatus(NationalScholarship nationalScholarship){
        return nationalScholarshipMapper.updateNationStatus(nationalScholarship);
    }

    /**
     * 删除国奖申请状态
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int deleteNation(NationalScholarship nationalScholarship){
        return nationalScholarshipMapper.deleteNation(nationalScholarship);
    }
}
