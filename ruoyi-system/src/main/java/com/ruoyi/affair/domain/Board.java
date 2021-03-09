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

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", studentId='" + studentId + '\'' +
                ", term='" + term + '\'' +
                ", address='" + address + '\'' +
                ", reason='" + reason + '\'' +
                ", connectMethod='" + connectMethod + '\'' +
                ", status=" + status +
                '}';
    }
}
