package com.ruoyi.info.domain;

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
    @Excel(name = "请假开始时间")
    private Date startTime;

    /** 请假结束时间 */
    @Excel(name = "请假结束时间")
    private Date endTime;

    /** 任课老师 */
    @Excel(name = "任课老师")
    private String teacher;

    /** 申请表状态 */
    @Excel(name = "申请表状态")
    private int status;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLeaveEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "TempLeave{" +
                "leaveId=" + leaveId +
                ", studentId='" + studentId + '\'' +
                ", reason='" + reason + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", teacher='" + teacher + '\'' +
                ", status=" + status +
                '}';
    }
}
