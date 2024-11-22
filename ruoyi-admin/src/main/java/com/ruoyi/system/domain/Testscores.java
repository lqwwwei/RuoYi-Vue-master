package com.ruoyi.system.domain;

import java.util.Date;

public class Testscores {
    private static final long serialVersionUID = 1L;

    /** 考试id */
    private Long id;

    private Long testrecordsId; //考试记录id

    private Long subjectId;

    private String userAnswer;

    private Double score;

    private Date createdAt;

    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public  void setTestrecordsId(Long testrecordsId){
        this.testrecordsId=testrecordsId;
    }
    public Long getTestrecordsId(){
        return testrecordsId;
    }
    public void setSubjectId(Long subjectId){
        this.subjectId=subjectId;
    }
    public Long getSubjectId(){
        return subjectId;
    }
    public void setUserAnswer(String userAnswer){
        this.userAnswer=userAnswer;
    }
    public  String getUserAnswer(){
        return  userAnswer;
    }
    public void setScore(Double score){
        this.score=score;
    }
    public Double getScore(){
        return score;
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
}
