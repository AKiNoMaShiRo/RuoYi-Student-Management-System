package com.ruoyi.notify.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Notify extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 未处理消息类型 */
    @Excel(name = "未处理消息类型")
    private String type;

    /** 未处理消息条数 */
    @Excel(name = "未处理消息条数")
    private int num;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Notify() {
    }

    public Notify(String type, int num) {
        this.type = type;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Notify{" +
                "type='" + type + '\'' +
                ", num=" + num +
                '}';
    }
}
