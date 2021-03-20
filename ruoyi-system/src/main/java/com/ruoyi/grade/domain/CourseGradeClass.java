package com.ruoyi.grade.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class CourseGradeClass extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 学年 */
    @Excel(name = "学年")
    private String learnYear;

    /** 学期 */
    @Excel(name = "学期")
    private String learnTerm;

    /** 课程类别 */
    @Excel(name = "课程类别")
    private String courseType;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程成绩 */
    @Excel(name = "课程成绩")
    private int courseGrade;

    /** 补考成绩 */
    @Excel(name = "补考成绩")
    private int resitGrade;

    /** 重新学习成绩 */
    @Excel(name = "重新学习成绩")
    private int retestGrade;

    /** 学分 */
    @Excel(name = "学分")
    private String coursePoint;

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

    public String getLearnYear() {
        return learnYear;
    }

    public void setLearnYear(String learnYear) {
        this.learnYear = learnYear;
    }

    public String getLearnTerm() {
        return learnTerm;
    }

    public void setLearnTerm(String learnTerm) {
        this.learnTerm = learnTerm;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }

    public int getResitGrade() {
        return resitGrade;
    }

    public void setResitGrade(int resitGrade) {
        this.resitGrade = resitGrade;
    }

    public int getRetestGrade() {
        return retestGrade;
    }

    public void setRetestGrade(int retestGrade) {
        this.retestGrade = retestGrade;
    }

    public String getCoursePoint() {
        return coursePoint;
    }

    public void setCoursePoint(String coursePoint) {
        this.coursePoint = coursePoint;
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
        return "CourseGradeClass{" +
                "studentId='" + studentId + '\'' +
                ", learnYear='" + learnYear + '\'' +
                ", learnTerm='" + learnTerm + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseGrade=" + courseGrade +
                ", resitGrade=" + resitGrade +
                ", retestGrade=" + retestGrade +
                ", coursePoint='" + coursePoint + '\'' +
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
