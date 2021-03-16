package com.ruoyi.info.mapper;

import com.ruoyi.info.domain.ClassInfo;

import java.util.List;

public interface ClassInfoMapper {

    /**
     * 查询班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public List<ClassInfo> selectClassInfo(ClassInfo classInfo);

    /**
     * 查询专业类别
     *
     * @param
     * @return 结果
     */
    public List<String> selectProfessionType();

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
     * 删除班级信息
     *
     * @param classId 班级信息
     * @return 结果
     */
    public int deleteClassInfo(Long classId);

}
