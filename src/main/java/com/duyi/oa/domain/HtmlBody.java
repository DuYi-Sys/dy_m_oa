package com.duyi.oa.domain;

import java.util.Date;

public class HtmlBody {
    private Long id;
    private String htmlTitle;
    private int type;
    private String htmlContent;
    private Date updateTime;

    public Long getId(){ return  this.id;}
    public void setId(Long id){ this.id = id;}

    public String getHtmlTitle(){ return  this.htmlTitle;}
    public void setHtmlTitle(String htmlTitle){ this.htmlTitle = htmlTitle;}

    public int getType(){ return  type;}
    public void setType(int type){this.type = type;}

    public String getHtmlContent(){return  this.htmlContent;}
    public void setHtmlContent(String htmlContent) { this.htmlContent = htmlContent;}

    public Date getUpdateTime(){ return  updateTime;}
    public void setUpdateTime(Date updateTime){ this.updateTime = updateTime;}

}
