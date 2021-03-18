package com.ruoyi.grade.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class LevelTest extends BaseEntity {
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

    /** 获得成绩时间 */
    @Excel(name = "获得成绩时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private String passTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

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

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "LevelTest{" +
                "studentId='" + studentId + '\'' +
                ", testType='" + testType + '\'' +
                ", testGrade='" + testGrade + '\'' +
                ", passTime='" + passTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
