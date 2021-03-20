package com.ruoyi.info.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class HeadmasterInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 班主任工号 */
    @Excel(name = "班主任工号")
    private String headmasterId;

    /** 班主任姓名 */
    @Excel(name = "班主任姓名")
    private String headmasterName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHeadmasterId() {
        return headmasterId;
    }

    public void setHeadmasterId(String headmasterId) {
        this.headmasterId = headmasterId;
    }

    public String getHeadmasterName() {
        return headmasterName;
    }

    public void setHeadmasterName(String headmasterName) {
        this.headmasterName = headmasterName;
    }

    @Override
    public String toString() {
        return "HeadmasterInfo{" +
                "userId=" + userId +
                ", headmasterId='" + headmasterId + '\'' +
                ", headmasterName='" + headmasterName + '\'' +
                '}';
    }

}
