package com.duyi.oa.domain;

import java.util.Date;

public class CssBody {
    private Long id;
    private String tag;
    private String classification;
    private String syntax;
    private String value;
    private String intro;
    private String example;
    private Date updateTime;

    public Long getId(){ return  this.id;}
    public void setId(Long id){ this.id = id; }

    public String getTag(){ return this.tag;}
    public void setTag(String tag) { this.tag = tag;}

    public String getClassification(){ return  this.classification;}
    public void setClassification(String classification){ this.classification = classification; }

    public String getSyntax(){ return  this.syntax;}
    public void setSyntax(String syntax){this.syntax = syntax;}

    public String getValue(){ return this.value; }
    public void setValue(String value){ this.value = value;}

    public String getIntro(){ return  intro;}
    public void setIntro(String intro){ this.intro = intro;}

    public String getExample(){ return  this.example;}
    public void setExample(String example){this.example = example;}

    public Date getUpdateTime(){ return  updateTime;}
    public void setUpdateTime(Date updateTime){ this.updateTime = updateTime;}
}
