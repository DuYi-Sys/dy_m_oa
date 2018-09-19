package com.duyi.oa.domain;

import java.util.Date;

public class JsBody {
    private int id;
    private String jsObj;
    private String jsDesc;
    private String jsAttr;
    private String jsFuns;
    private Date updateTime;

    public int getId(){ return  id;}
    public void setId(int id){  this.id = id; }

    public String getJsObj(){ return jsObj; }
    public void setJsObj(String jsObj){ this.jsObj = jsObj;}

    public String getJsDesc(){ return  jsDesc;}
    public void setJsDesc(String jsDesc) { this.jsDesc = jsDesc;}

    public String getJsAttr(){ return jsAttr;}
    public void setJsAttr(String jsAttr){this.jsAttr = jsAttr;}

    public String getJsFuns(){ return jsFuns;}
    public void setJsFuns(String jsFuns){ this.jsFuns = jsFuns;}

    public Date getUpdateTime(){ return  updateTime;}
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}