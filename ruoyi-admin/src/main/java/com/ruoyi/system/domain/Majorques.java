package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class Majorques extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 大题id */
    private Integer id;
    /** 大题序号 */
    private String dicVal1;
    /** 小题序号（0表示大题描述） */
    private String dicVal2;
    /** 大题标题+内容 */
    private String dicVal3;
    /** 大题性质 */
    private String dicVal4;
    /** 大题小题数 */
    private String dicVal5;
    /** 大题每小题分数 */
    private String dicVal6;
    /** 大题总分 */
    private String dicVal7;

    private String dicVal8;

    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicVal1() {
        return dicVal1;
    }

    public void setDicVal1(String dicVal1) {
        this.dicVal1 = dicVal1;
    }

    public String getDicVal2() {
        return dicVal2;
    }

    public void setDicVal2(String dicVal2) {
        this.dicVal2 = dicVal2;
    }

    public String getDicVal3() {
        return dicVal3;
    }

    public void setDicVal3(String dicVal3) {
        this.dicVal3 = dicVal3;
    }

    public String getDicVal4() {
        return dicVal4;
    }

    public void setDicVal4(String dicVal4) {
        this.dicVal4 = dicVal4;
    }

    public String getDicVal5() {
        return dicVal5;
    }

    public void setDicVal5(String dicVal5) {
        this.dicVal5 = dicVal5;
    }

    public String getDicVal6() {
        return dicVal6;
    }

    public void setDicVal6(String dicVal6) {
        this.dicVal6 = dicVal6;
    }

    public String getDicVal7() {
        return dicVal7;
    }

    public void setDicVal7(String dicVal7) {
        this.dicVal7 = dicVal7;
    }

    public String getDicVal8() {
        return dicVal8;
    }

    public void setDicVal8(String dicVal8) {
        this.dicVal8 = dicVal8;
    }

    @Override
    public String toString() {
        return "Majorques{" +
                "id=" + id +
                ", dicVal1='" + dicVal1 + '\'' +
                ", dicVal2=" + dicVal2 +
                ", dicVal3='" + dicVal3 + '\'' +
                ", dicVal4='" + dicVal4 + '\'' +
                ", dicVal5=" + dicVal5 +
                ", dicVal6=" + dicVal6 +
                ", dicVal7=" + dicVal7 +
                '}';
    }
}