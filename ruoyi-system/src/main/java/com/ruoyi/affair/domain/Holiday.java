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
    private Date leaveStartTime;

    /** 拟回校时间 */
    @Excel(name = "拟回校时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date leaveEndTime;

    /** 外出地址 */
    @Excel(name = "外出地址")
    private String address;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private int status;

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

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
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

    @Override
    public String toString() {
        return "Holiday{" +
                "goId=" + goId +
                ", studentId='" + studentId + '\'' +
                ", term='" + term + '\'' +
                ", holidayType='" + holidayType + '\'' +
                ", destination='" + destination + '\'' +
                ", leaveStartTime=" + leaveStartTime +
                ", leaveEndTime=" + leaveEndTime +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
