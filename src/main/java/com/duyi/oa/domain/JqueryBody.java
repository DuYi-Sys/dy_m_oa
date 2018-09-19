package com.duyi.oa.domain;
import java.util.Date;
public class JqueryBody {
    private Long id;
    private String attribute;
    private String firstClass;
    private String secondClass;
    private String retValue;
    private String summarize;
    private String parameter;
    private String example;
    private Date updateTime;

    public Long getId(){ return  id;}
    public void setId(Long id){this.id = id;}

    public String getAttribute(){ return  this.attribute;}
    public void getAttribute(String attribute){ this.attribute = attribute;}

    public String getFirstClass(){ return this.firstClass;}
    public void setFirstClass(String firstClass){ this.firstClass = firstClass;}

    public String getSecondClass(){ return  this.secondClass;}
    public void setSecondClass(String secondClass){ this.secondClass = secondClass;}

    public String getRetValue(){ return  retValue;}
    public void setRetValue(String retValue) { this.retValue = retValue;}

    public String getSummarize(){ return  this.summarize;}
    public void setSummarize(String summarize){ this.summarize = summarize;}

    public String getParameter(){ return  parameter;}
    public void setParameter(String parameter){this.parameter = parameter;}

    public String getExample(){ return  this.example;}
    public void setExample(String example){ this.example = example;}

    public Date getUpdateTime(){ return  this.updateTime;}
    public void setUpdateTime(Date updateTime){ this.updateTime = updateTime;}
}
