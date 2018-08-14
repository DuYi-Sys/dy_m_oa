package com.duyi.oa.domain;

import java.util.Date;

public class IntentionBody {
    private Long id=0L;
    private String weiXin; // 微信号
    private String weiXinName; // 微信名
    private int group; // 公益群号
    private String platform; // 引流平台
    private int identity; // 身份 1:在校 2:在职3:离职
    private int age; // 年龄
    private int progress; // 学习进度 1:基础 2:就业 3:进阶
    private int haveTrain; // 1/0 有培训经历/没有培训经历
    private int haveOpen; // 1/0 听过/没听过 免费公开课
    private String requirements; // 学习需求
    private int communicateNum; // 沟通次数
    private String characteristics; // 学生特征
    private String visitRecord; // 回访记录
    private int havePay; // 1/0 付款/未付款
    private Long uploadId; // 上传人id
    private Long claimId; // 审核人id
    private int status; // 认领状态: 1: 未认领 2: 已认领
    private int acceptance;
    private Date iniRegistrationTime; // 初始登记时间
    private Date lastLinkupTime; // 最后沟通日期

    public Long getId(){ return  this.id;}
    public void setId(Long id) { this.id = id;}

    public int getAcceptance(){return  this.acceptance;}
    public void setAcceptance(int acceptance){this.acceptance = acceptance;}

    public String getWeiXin(){return  this.weiXin;}
    public void setWeiXin(String weiXin){ this.weiXin = weiXin;}

    public String getWeiXinName(){ return  this.weiXinName;}
    public void setWeiXinName(String weiXinName){ this.weiXinName = weiXinName;}

    public int getGroup(){ return  this.group;}
    public void setGroup(int group){ this.group = group;}

    public String getPlatform(){ return  this.platform;}
    public void setPlatform(String platform){ this.platform = platform;}

    public int getIdentity(){ return this.identity;}
    public void setIdentity(int identity){ this .identity = identity; }

    public int getAge(){ return  this.age;}
    public void setAge(int age){ this.age = age;}

    public int getProgress(){return this.progress;}
    public void setProgress(int progress){ this.progress = progress;}

    public int getHaveTrain(){ return  haveTrain;}
    public void setHaveTrain(int haveTrain){this.haveTrain = haveTrain;}

    public int getHaveOpen(){ return  this.haveOpen;}
    public void setHaveOpen(int haveOpen){ this.haveOpen = haveOpen;}

    public String getRequirements(){ return  this.requirements;}
    public void setRequirements(String requirements){ this.requirements = requirements;}

    public int getCommunicateNum(){ return  this.communicateNum;}
    public void setCommunicateNum(int communicateNum){this.communicateNum = communicateNum;}

    public String getCharacteristics(){return  this.characteristics;}
    public void setCharacteristics(String characteristics){ this.characteristics = characteristics;}

    public String getVisitRecord(){return  this.visitRecord;}
    public void setVisitRecord(String visitRecord){this.visitRecord = visitRecord;}

    public int getHavePay(){return  this.havePay;}
    public void setHavePay(int havePay){ this.havePay = havePay;}

    public Long getUploadId(){return this.uploadId;}
    public void setUploadId(Long uploadId){this.uploadId = uploadId;}

    public Long getClaimId(){ return  this.claimId;}
    public void setClaimId(Long claimId){ this.claimId = claimId;}

    public int getStatus(){return  status;}
    public void setStatus(int status){ this.status = status;}

    public Date getIniRegistrationTime(){return  iniRegistrationTime;}
    public void setIniRegistrationTime(Date iniRegistrationTime){this.iniRegistrationTime = iniRegistrationTime;}

    public Date getLastLinkupTime(){return  lastLinkupTime;}
    public void setLastLinkupTime(Date lastLinkupTime){this.lastLinkupTime = lastLinkupTime;}
}
