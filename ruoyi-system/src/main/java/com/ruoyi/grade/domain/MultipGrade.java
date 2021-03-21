package com.ruoyi.grade.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class MultipGrade extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String stuName;

    /** 学生班级ID */
    @Excel(name = "学生班级ID")
    private Long classId;

    /** 专业 */
    @Excel(name = "专业")
    private String profession;

    /** 年级 */
    @Excel(name = "年级")
    private int grade;

    /** 班级序号 */
    @Excel(name = "班级序号")
    private int classNum;

    /** 辅导员工号 */
    @Excel(name = "辅导员工号")
    private String instructorId;

    /** 班主任工号 */
    @Excel(name = "班主任工号")
    private String headmasterId;

    /** 专业人数 */
    @Excel(name = "专业人数")
    private String profeSum;

    /** 必修与选修折算成绩之和 */
    @Excel(name = "必修与选修折算成绩之和")
    private String gradeSum;

    /** 专业排名 */
    @Excel(name = "专业排名")
    private String profeRank;

    /** 综测总分 */
    @Excel(name = "综测总分")
    private String multipSum;

    /** 综测排名 */
    @Excel(name = "综测排名")
    private String multipRank;

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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public String getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(String headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getProfeSum() {
        return profeSum;
    }

    public void setProfeSum(String profeSum) {
        this.profeSum = profeSum;
    }

    public String getGradeSum() {
        return gradeSum;
    }

    public void setGradeSum(String gradeSum) {
        this.gradeSum = gradeSum;
    }

    public String getProfeRank() {
        return profeRank;
    }

    public void setProfeRank(String profeRank) {
        this.profeRank = profeRank;
    }

    public String getMultipSum() {
        return multipSum;
    }

    public void setMultipSum(String multipSum) {
        this.multipSum = multipSum;
    }

    public String getMultipRank() {
        return multipRank;
    }

    public void setMultipRank(String multipRank) {
        this.multipRank = multipRank;
    }

    @Override
    public String toString() {
        return "MultipGrade{" +
                "studentId='" + studentId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", classId=" + classId +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", instructorId='" + instructorId + '\'' +
                ", headmasterId='" + headmasterId + '\'' +
                ", profeSum='" + profeSum + '\'' +
                ", gradeSum='" + gradeSum + '\'' +
                ", profeRank='" + profeRank + '\'' +
                ", multipSum='" + multipSum + '\'' +
                ", multipRank='" + multipRank + '\'' +
                '}';
    }
}
