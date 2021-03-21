package com.ruoyi.grade.service.Impl;

import com.ruoyi.grade.domain.MultipGrade;
import com.ruoyi.grade.mapper.MultipGradeMapper;
import com.ruoyi.grade.service.IMultipGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultipGradeServiceImpl implements IMultipGradeService {

    @Autowired
    private MultipGradeMapper multipGradeMapper;

    /**
     *
     * 批量查询综测成绩信息
     *
     * @param multipInfo 查询信息
     * @return 结果
     */
    public List<MultipGrade> selectInfo(MultipGrade multipInfo){
        return multipGradeMapper.selectInfo(multipInfo);
    }

}
