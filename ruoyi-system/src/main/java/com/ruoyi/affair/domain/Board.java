package com.ruoyi.affair.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Board extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 外宿申请ID */
    @Excel(name = "外宿申请ID")
    private Long boardId;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 学期 */
    @Excel(name = "学期")
    private String term;

    /** 外宿地址 */
    @Excel(name = "外宿地址")
    private String address;

    /** 外宿原因 */
    @Excel(name = "外宿原因")
    private String reason;

    /** 家长联系方式 */
    @Excel(name = "家长联系方式")
    private String connectMethod;

    /** 申请状态 */
    @Excel(name = "申请状态")
    private int status;

    /** 辅导员工号 */
    @Excel(name = "辅导员工号")
    private String instructorId;

    /** 辅导员姓名 */
    @Excel(name = "辅导员姓名")
    private String instructorName;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getConnectMethod() {
        return connectMethod;
    }

    public void setConnectMethod(String connectMethod) {
        this.connectMethod = connectMethod;
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
        return "Board{" +
                "boardId=" + boardId +
                ", studentId='" + studentId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", term='" + term + '\'' +
                ", address='" + address + '\'' +
                ", reason='" + reason + '\'' +
                ", connectMethod='" + connectMethod + '\'' +
                ", status=" + status +
                ", instructorId='" + instructorId + '\'' +
                ", instructorName='" + instructorName + '\'' +
                '}';
    }

}
