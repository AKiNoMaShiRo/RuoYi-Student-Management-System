package com.ruoyi.info.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.info.domain.StuInfo;
import com.ruoyi.info.mapper.StuInfoMapper;
import com.ruoyi.info.service.IStuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生信息 业务层处理
 *
 * @author akino
 */
@Service
public class StuInfoServiceImpl implements IStuInfoService {

    @Autowired
    private StuInfoMapper infoMapper;

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
        return  infoMapper.updateStu(stuInfo);
    }
}
