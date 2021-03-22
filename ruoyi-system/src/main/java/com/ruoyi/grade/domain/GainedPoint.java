package com.ruoyi.grade.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class GainedPoint extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 课程类别 */
    @Excel(name = "课程类别")
    private String courseType;

    /** 已获得学分 */
    @Excel(name = "已获得学分")
    private String gainedPoint;

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getGainedPoint() {
        return gainedPoint;
    }

    public void setGainedPoint(String gainedPoint) {
        this.gainedPoint = gainedPoint;
    }

    @Override
    public String toString() {
        return "GainedPoint{" +
                "courseType='" + courseType + '\'' +
                ", gainedPoint='" + gainedPoint + '\'' +
                '}';
    }
}
