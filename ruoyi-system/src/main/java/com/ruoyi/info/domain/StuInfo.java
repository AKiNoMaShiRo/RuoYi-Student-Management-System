package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class StuInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学号 */
    @Excel(name = "学号")
    private String studentId;

    /** 学院 */
    @Excel(name = "学院")
    private String department;

    /** 专业 */
    @Excel(name = "专业")
    private String profession;

    /** 年级 */
    @Excel(name = "年级")
    private int grade;

    /** 班级 */
    @Excel(name = "班级")
    private int classNum;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 毕业意向调查 */
    @Excel(name = "毕业意向调查")
    private int graduIntention;

    /** 是否已缴费注册 */
    @Excel(name = "是否已缴费注册")
    private int registration;

    /** 导师姓名 */
    @Excel(name = "导师姓名")
    private String teacher;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** QQ */
    @Excel(name = "QQ")
    private String qq;

    /** 微信 */
    @Excel(name = "微信")
    private String weChat;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birthday;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private int politicsStatus;

    /** 加入时间 */
    @Excel(name = "加入时间")
    private String joinTime;

    /** 生源地区 */
    @Excel(name = "生源地区")
    private String studentOrigin;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String familyAddress;

    /** 家庭电话 */
    @Excel(name = "家庭电话")
    private String familyPhoneNumber;

    /** 家庭联系人 */
    @Excel(name = "家庭联系人")
    private String familyContacts;

    /** 家庭邮编 */
    @Excel(name = "家庭邮编")
    private String familyPostcode;

    /** 毕业中学 */
    @Excel(name = "毕业中学")
    private String graduSchool;

    /** 毕业中学邮编 */
    @Excel(name = "毕业中学邮编")
    private String graduSchoolPostcode;

    /** 曾用名 */
    @Excel(name = "曾用名")
    private String usedName;

    /** 籍贯 */
    @Excel(name = "籍贯")
    private String nativePlace;

    /** 医保卡号 */
    @Excel(name = "医保卡号")
    private String healthCard;

    /** 四级成绩 */
    @Excel(name = "四级成绩")
    private int cet4;

    /** 六级成绩 */
    @Excel(name = "六级成绩")
    private int cet6;

    /** 英文名 */
    private String englishName;

    /** 信息上报项目 */
    @Excel(name = "信息上报项目")
    private int reportItem;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGraduIntention() {
        return graduIntention;
    }

    public void setGraduIntention(int graduIntention) {
        this.graduIntention = graduIntention;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(int politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getStudentOrigin() {
        return studentOrigin;
    }

    public void setStudentOrigin(String studentOrigin) {
        this.studentOrigin = studentOrigin;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getFamilyPhoneNumber() {
        return familyPhoneNumber;
    }

    public void setFamilyPhoneNumber(String familyPhoneNumber) {
        this.familyPhoneNumber = familyPhoneNumber;
    }

    public String getFamilyContacts() {
        return familyContacts;
    }

    public void setFamilyContacts(String familyContacts) {
        this.familyContacts = familyContacts;
    }

    public String getFamilyPostcode() {
        return familyPostcode;
    }

    public void setFamilyPostcode(String familyPostcode) {
        this.familyPostcode = familyPostcode;
    }

    public String getGraduSchool() {
        return graduSchool;
    }

    public void setGraduSchool(String graduSchool) {
        this.graduSchool = graduSchool;
    }

    public String getGraduSchoolPostcode() {
        return graduSchoolPostcode;
    }

    public void setGraduSchoolPostcode(String graduSchoolPostcode) {
        this.graduSchoolPostcode = graduSchoolPostcode;
    }

    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public int getCet4() {
        return cet4;
    }

    public void setCet4(int cet4) {
        this.cet4 = cet4;
    }

    public int getCet6() {
        return cet6;
    }

    public void setCet6(int cet6) {
        this.cet6 = cet6;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getReportItem() {
        return reportItem;
    }

    public void setReportItem(int reportItem) {
        this.reportItem = reportItem;
    }

    @Override
    public String toString() {
        return "StuInfo{" +
                "studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", name='" + name + '\'' +
                ", graduIntention=" + graduIntention +
                ", registration=" + registration +
                ", teacher='" + teacher + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", weChat='" + weChat + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nation='" + nation + '\'' +
                ", politicsStatus=" + politicsStatus +
                ", joinTime='" + joinTime + '\'' +
                ", studentOrigin='" + studentOrigin + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                ", familyPhoneNumber='" + familyPhoneNumber + '\'' +
                ", familyContacts='" + familyContacts + '\'' +
                ", familyPostcode='" + familyPostcode + '\'' +
                ", graduSchool='" + graduSchool + '\'' +
                ", graduSchoolPostcode='" + graduSchoolPostcode + '\'' +
                ", usedName='" + usedName + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", healthCard='" + healthCard + '\'' +
                ", cet4=" + cet4 +
                ", cet6=" + cet6 +
                ", englishName='" + englishName + '\'' +
                ", reportItem=" + reportItem +
                '}';
    }
}
