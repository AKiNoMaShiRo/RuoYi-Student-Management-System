package com.ruoyi.affair.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class Holiday extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 节假日去向信息ID */
    @Excel(name = "节假日去向信息ID")
    private Long goId;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 学期 */
    @Excel(name = "学期")
    private String term;

    /** 节假日 */
    @Excel(name = "节假日")
    private String holidayType;

    /** 去向类别 */
    @Excel(name = "去向类别")
    private String destination;

    /** 离校时间 */
    @Excel(name = "离校时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date holidayStartTime;

    /** 拟回校时间 */
    @Excel(name = "拟回校时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date holidayEndTime;

    /** 外出地址 */
    @Excel(name = "外出地址")
    private String address;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private int status;

    /** 辅导员工号 */
    @Excel(name = "辅导员工号")
    private String instructorId;

    /** 辅导员姓名 */
    @Excel(name = "辅导员姓名")
    private String instructorName;

    public Long getGoId() {
        return goId;
    }

    public void setGoId(Long goId) {
        this.goId = goId;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getHolidayStartTime() {
        return holidayStartTime;
    }

    public void setHolidayStartTime(Date holidayStartTime) {
        this.holidayStartTime = holidayStartTime;
    }

    public Date getHolidayEndTime() {
        return holidayEndTime;
    }

    public void setHolidayEndTime(Date holidayEndTime) {
        this.holidayEndTime = holidayEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "Holiday{" +
                "goId=" + goId +
                ", studentId='" + studentId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", term='" + term + '\'' +
                ", holidayType='" + holidayType + '\'' +
                ", destination='" + destination + '\'' +
                ", holidayStartTime=" + holidayStartTime +
                ", holidayEndTime=" + holidayEndTime +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                '}';
    }

}
