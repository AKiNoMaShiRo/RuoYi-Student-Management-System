package com.ruoyi.scholarship.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class DifficultStu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 困难等级 */
    @Excel(name = "困难等级")
    private String diffLevel;

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

    /** 辅导员姓名 */
    @Excel(name = "辅导员姓名")
    private String instructorName;

    /** 班主任工号 */
    @Excel(name = "班主任工号")
    private String headmasterId;

    /** 班主任姓名 */
    @Excel(name = "班主任姓名")
    private String headmasterName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getDiffLevel() {
        return diffLevel;
    }

    public void setDiffLevel(String diffLevel) {
        this.diffLevel = diffLevel;
    }

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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(String headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getHeadmasterName() {
        return headmasterName;
    }

    public void setHeadmasterName(String headmasterName) {
        this.headmasterName = headmasterName;
    }

    @Override
    public String toString() {
        return "DifficultStu{" +
                "studentId='" + studentId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", diffLevel='" + diffLevel + '\'' +
                ", classId=" + classId +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", headmasterId='" + headmasterId + '\'' +
                ", headmasterName='" + headmasterName + '\'' +
                '}';
    }

}
