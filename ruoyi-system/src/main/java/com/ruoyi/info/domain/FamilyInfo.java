package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class FamilyInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 成员ID */
    @Excel(name = "成员ID")
    private Long memberId;

    /** 所属学生学号 */
    @Excel(name = "所属学生学号")
    private String relativeStu;

    /** 称谓 */
    @Excel(name = "称谓")
    private String appellation;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String identityCard;

    /** 健康状况 */
    @Excel(name = "健康状况")
    private String health;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String company;

    /** 职位 */
    @Excel(name = "职位")
    private String duty;

    /** 邮编 */
    @Excel(name = "邮编")
    private String postCode;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicsStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRelativeStu() {
        return relativeStu;
    }

    public void setRelativeStu(String relativeStu) {
        this.relativeStu = relativeStu;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
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
        return "FamilyInfo{" +
                "memberId=" + memberId +
                ", relativeStu='" + relativeStu + '\'' +
                ", appellation='" + appellation + '\'' +
                ", name='" + name + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", health='" + health + '\'' +
                ", company='" + company + '\'' +
                ", duty='" + duty + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
