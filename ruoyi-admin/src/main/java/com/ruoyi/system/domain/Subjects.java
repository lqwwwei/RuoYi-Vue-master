package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 试题管理对象 subjects
 *
 * @author ruoyi
 * @date 2024-10-28
 */
public class Subjects extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 试题id */
    private Long id;

    /** 大题序号 */
    @Excel(name = "大题序号")
    private String part;

    /** 小题序号 */
    @Excel(name = "小题序号")
    private Integer order;

    /** 试卷id */
//    @Excel(name = "试卷id")
    private Long paperId;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String content;

    /** 选项A描述 */
    @Excel(name = "选项A描述")
    private String optionA;

    /** 选项B描述 */
    @Excel(name = "选项B描述")
    private String optionB;

    /** 选项C描述 */
    @Excel(name = "选项C描述")
    private String optionC;

    /** 选项D描述 */
    @Excel(name = "选项D描述")
    private String optionD;

    /** 题目类型 0单选 1判断 2多选 */
    @Excel(name = "题目类型", readConverterExp = "0=单选,1=判断,2=多选")
    private Integer type;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String answer;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPart() {
        return part;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("part", getPart())
                .append("order", getOrder())
                .append("paperId", getPaperId())
                .append("content", getContent())
                .append("optionA", getOptionA())
                .append("optionB", getOptionB())
                .append("optionC", getOptionC())
                .append("optionD", getOptionD())
                .append("type", getType())
                .append("answer", getAnswer())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }

}