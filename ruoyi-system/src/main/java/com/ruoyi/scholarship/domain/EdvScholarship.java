package com.ruoyi.scholarship.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class EdvScholarship extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 申请编号 */
    @Excel(name = "申请编号")
    private Long scholarshipId;

    /** 学年 */
    @Excel(name = "学年")
    private String learnYear;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 是否破格 */
    @Excel(name = "是否破格")
    private int isFit;

    /** 困难等级 */
    @Excel(name = "困难等级")
    private String diffLevel;

    /** 专业人数 */
    @Excel(name = "专业人数")
    private int profeSum;

    /** 体测成绩 */
    @Excel(name = "体测成绩")
    private double sportGrade;

    /** 综合测评排名 */
    @Excel(name = "综合测评排名")
    private int multipRank;

    /** 综合测评排名百分比 */
    @Excel(name = "综合测评排名百分比")
    private double multipRankRate;

    /** 省级及以上表彰或成果 */
    @Excel(name = "省级及以上表彰或成果")
    private String porvincePrize;

    /** 校级表彰或成果 */
    @Excel(name = "校级表彰或成果")
    private String schoolPrize;

    /** 是否通过 */
    @Excel(name = "是否通过")
    private int status;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long classId;

    /** 所属专业 */
    @Excel(name = "所属专业")
    private String profession;

    /** 年级 */
    @Excel(name = "年级")
    private int grade;

    /** 班级 */
    @Excel(name = "班级")
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

    public Long getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(Long scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public String getLearnYear() {
        return learnYear;
    }

    public void setLearnYear(String learnYear) {
        this.learnYear = learnYear;
    }

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

    public int getIsFit() {
        return isFit;
    }

    public void setIsFit(int isFit) {
        this.isFit = isFit;
    }

    public String getDiffLevel() {
        return diffLevel;
    }

    public void setDiffLevel(String diffLevel) {
        this.diffLevel = diffLevel;
    }

    public int getProfeSum() {
        return profeSum;
    }

    public void setProfeSum(int profeSum) {
        this.profeSum = profeSum;
    }

    public double getSportGrade() {
        return sportGrade;
    }

    public void setSportGrade(double sportGrade) {
        this.sportGrade = sportGrade;
    }

    public int getMultipRank() {
        return multipRank;
    }

    public void setMultipRank(int multipRank) {
        this.multipRank = multipRank;
    }

    public double getMultipRankRate() {
        return multipRankRate;
    }

    public void setMultipRankRate(double multipRankRate) {
        this.multipRankRate = multipRankRate;
    }

    public String getPorvincePrize() {
        return porvincePrize;
    }

    public void setPorvincePrize(String porvincePrize) {
        this.porvincePrize = porvincePrize;
    }

    public String getSchoolPrize() {
        return schoolPrize;
    }

    public void setSchoolPrize(String schoolPrize) {
        this.schoolPrize = schoolPrize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "EdvScholarship{" +
                "scholarshipId=" + scholarshipId +
                ", learnYear='" + learnYear + '\'' +
                ", studentId='" + studentId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", isFit=" + isFit +
                ", diffLevel='" + diffLevel + '\'' +
                ", profeSum=" + profeSum +
                ", sportGrade=" + sportGrade +
                ", multipRank=" + multipRank +
                ", multipRankRate=" + multipRankRate +
                ", porvincePrize='" + porvincePrize + '\'' +
                ", schoolPrize='" + schoolPrize + '\'' +
                ", status=" + status +
                ", classId=" + classId +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", headmasterId='" + headmasterId + '\'' +
                '}';
    }
}
