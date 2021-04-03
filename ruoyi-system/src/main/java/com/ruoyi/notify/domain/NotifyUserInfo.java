package com.ruoyi.notify.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class NotifyUserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String studentId;

    /** 辅导员工号 */
    @Excel(name = "辅导员工号")
    private String instructorId;

    /** 班主任工号 */
    @Excel(name = "班主任工号")
    private String headmasterId;

    /** 学生学号 */
    @Excel(name = "副书记账号")
    private String masterId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Override
    public String toString() {
        return "NotifyUserInfo{" +
                "studentId='" + studentId + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", headmasterId='" + headmasterId + '\'' +
                ", masterId='" + masterId + '\'' +
                '}';
    }
}
