package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class ClassInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 班级信息编号 */
    @Excel(name = "班级信息编号")
    private Long classId;

    /** 所属学院 */
    @Excel(name = "所属学院")
    private String department;

    /** 所属专业 */
    @Excel(name = "所属专业")
    private String profession;

    /** 所属年级 */
    @Excel(name = "所属年级")
    private int grade;

    /** 班级序号 */
    @Excel(name = "班级序号")
    private int classNum;

    /** 辅导员工号 */
    @Excel(name = "辅导员工号")
    private String instructorId;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classId=" + classId +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", instructorId='" + instructorId + '\'' +
                '}';
    }
}
