package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class StuBaseInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 学院 */
    @Excel(name = "学院")
    private String department;

    /** 专业 */
    @Excel(name = "专业")
    private String profession;

    /** 年级 */
    @Excel(name = "年级")
    private int grade;

    /** 班级 */
    @Excel(name = "班级")
    private int classNum;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StuBaseInfo{" +
                "studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", name='" + name + '\'' +
                '}';
    }
}
