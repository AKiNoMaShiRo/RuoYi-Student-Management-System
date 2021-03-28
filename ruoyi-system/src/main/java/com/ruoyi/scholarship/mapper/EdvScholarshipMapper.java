package com.ruoyi.scholarship.mapper;

import com.ruoyi.scholarship.domain.EdvScholarship;

import java.util.List;

public interface EdvScholarshipMapper {

    /**
     * 查询国励申请
     *
     * @param edvScholarship 查询信息
     * @return 结果
     */
    public List<EdvScholarship> selectEdvApply(EdvScholarship edvScholarship);

    /**
     * 新增国励申请
     *
     * @param edvScholarship 学号
     * @return 结果
     */
    public int insertEdvApply(EdvScholarship edvScholarship);

    /**
     * 更新国励申请状态
     *
     * @param edvScholarship 学号
     * @return 结果
     */
    public int updateEdvStatus(EdvScholarship edvScholarship);
}
