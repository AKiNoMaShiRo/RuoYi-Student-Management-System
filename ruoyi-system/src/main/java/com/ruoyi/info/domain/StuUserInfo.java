package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class StuUserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String userName;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String nickName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String avatar;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String password;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long classId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private Long studentId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private Long stuName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStuName() {
        return stuName;
    }

    public void setStuName(Long stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "StuUserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", classId=" + classId +
                ", studentId=" + studentId +
                ", stuName=" + stuName +
                '}';
    }
}
