package com.ruoyi.scholarship.service.Impl;

import com.ruoyi.scholarship.domain.EdvScholarship;
import com.ruoyi.scholarship.mapper.EdvScholarshipMapper;
import com.ruoyi.scholarship.service.IEdvScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdvScholarshipServiceImpl implements IEdvScholarshipService {

    @Autowired
    private EdvScholarshipMapper edvScholarshipMapper;


    /**
     * 查询国励申请
     *
     * @param edvScholarship 查询信息
     * @return 结果
     */
    public List<EdvScholarship> selectEdvApply(EdvScholarship edvScholarship){
        return edvScholarshipMapper.selectEdvApply(edvScholarship);
    }

    /**
     * 新增国励申请
     *
     * @param edvScholarship 新增信息
     * @return 结果
     */
    public int insertEdvApply(EdvScholarship edvScholarship){
        return edvScholarshipMapper.insertEdvApply(edvScholarship);
    }
}
