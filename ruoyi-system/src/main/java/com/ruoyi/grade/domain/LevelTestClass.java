package com.ruoyi.grade.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class LevelTestClass extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 考试类别 */
    @Excel(name = "考试类别")
    private String testType;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private String testGrade;

    /** 是否通过 */
    @Excel(name = "是否通过")
    private String isPass;

    /** 获得成绩时间 */
    @Excel(name = "获得成绩时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private String testTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestGrade() {
        return testGrade;
    }

    public void setTestGrade(String testGrade) {
        this.testGrade = testGrade;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    @Override
    public String toString() {
        return "LevelTestClass{" +
                "studentId='" + studentId + '\'' +
                ", testType='" + testType + '\'' +
                ", testGrade='" + testGrade + '\'' +
                ", isPass='" + isPass + '\'' +
                ", testTime='" + testTime + '\'' +
                ", remark='" + remark + '\'' +
                ", stuName='" + stuName + '\'' +
                ", classId=" + classId +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                '}';
    }
}
