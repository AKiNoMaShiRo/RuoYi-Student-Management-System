package com.ruoyi.affair.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class TempLeave extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 请假单编号 */
    @Excel(name = "请假单编号")
    private Long leaveId;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String reason;

    /** 请假开始时间 */
    @Excel(name = "请假开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date leaveStartTime;

    /** 请假结束时间 */
    @Excel(name = "请假结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date leaveEndTime;

    /** 任课老师 */
    @Excel(name = "任课老师")
    private String teacher;

    /** 申请表状态 */
    @Excel(name = "申请表状态")
    private int status;

    /** 辅导员编号 */
    @Excel(name = "辅导员编号")
    private String instructorId;

    /** 辅导员姓名 */
    @Excel(name = "辅导员姓名")
    private String instructorName;

    /** 任课老师编号 */
    @Excel(name = "任课老师编号")
    private String teacherId;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "TempLeave{" +
                "leaveId=" + leaveId +
                ", studentId='" + studentId + '\'' +
                ", reason='" + reason + '\'' +
                ", startTime=" + leaveStartTime +
                ", leaveEndTime=" + leaveEndTime +
                ", teacher='" + teacher + '\'' +
                ", status=" + status +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
