package com.ruoyi.info.service;

import com.ruoyi.info.domain.ClassInfo;

import java.util.List;

/**
 * 班级信息管理 业务层
 *
 * @author akino
 */
public interface IClassInfoService {

    /**
     * 查询所有班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public List<ClassInfo> selectAllClassInfo(ClassInfo classInfo);

    /**
     * 查询班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public List<ClassInfo> selectClassInfo(ClassInfo classInfo);

    /**
     * 新增班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public int insertClassInfo(ClassInfo classInfo);

    /**
     * 编辑班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public int updateClassInfo(ClassInfo classInfo);

    /**
     * 编辑班级信息
     *
     * @param classId 班级信息
     * @return 结果
     */
    public int deleteClassInfo(Long classId);

}
