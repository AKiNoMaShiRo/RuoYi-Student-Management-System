package com.ruoyi.scholarship.service;

import com.ruoyi.scholarship.domain.NationalScholarship;

import java.util.List;

public interface INationalScholarshipService {

    /**
     * 查询国奖申请
     *
     * @param nationalScholarship 查询信息
     * @return 结果
     */
    public List<NationalScholarship> selectNationalApply(NationalScholarship nationalScholarship);

    /**
     * 查询通过初审的国奖申请
     *
     * @param nationalScholarship 查询信息
     * @return 结果
     */
    public List<NationalScholarship> selectNationRecheck(NationalScholarship nationalScholarship);

    /**
     * 新增国奖申请
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int insertNationalApply(NationalScholarship nationalScholarship);

    /**
     * 更新国奖申请状态
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int updateNationStatus(NationalScholarship nationalScholarship);

    /**
     * 删除国奖申请状态
     *
     * @param nationalScholarship 学号
     * @return 结果
     */
    public int deleteNation(NationalScholarship nationalScholarship);
}
