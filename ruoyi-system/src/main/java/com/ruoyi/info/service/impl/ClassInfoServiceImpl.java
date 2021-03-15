package com.ruoyi.info.service.impl;

import com.ruoyi.info.domain.ClassInfo;
import com.ruoyi.info.mapper.ClassInfoMapper;
import com.ruoyi.info.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级信息管理 业务层处理
 *
 * @author akino
 */
@Service
public class ClassInfoServiceImpl implements IClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;


    /**
     * 查询所有班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public List<ClassInfo> selectAllClassInfo(ClassInfo classInfo){
        return classInfoMapper.selectAllClassInfo(classInfo);
    }

    /**
     * 查询班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public List<ClassInfo> selectClassInfo(ClassInfo classInfo){
        return classInfoMapper.selectClassInfo(classInfo);
    }

    /**
     * 新增班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int insertClassInfo(ClassInfo classInfo){
        return classInfoMapper.insertClassInfo(classInfo);
    }

    /**
     * 编辑班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int updateClassInfo(ClassInfo classInfo){
        return classInfoMapper.updateClassInfo(classInfo);
    }

    /**
     * 编辑班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int deleteClassInfo(Long classId){
        return classInfoMapper.deleteClassInfo(classId);
    }

}
