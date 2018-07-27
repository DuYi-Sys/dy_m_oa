package com.duyi.oa.domain;

public class StudentBody {
    private Long id = 0L;
    private String name;
    private int sex;
    private int age;
    private  String phone;
    private  String weixin;
    private String qq;
    private int uploadId;
    private String address;
    private int type; // 1/0 on line/off line
    private int level; // 0: 1: 付费意愿不强, 2: 有付费意愿, 3: 付费意愿强烈
    private int status; // 1/2 not paid/charges paid

    public Long getId(){ return  id;}
    public void setId(Long id){ this.id = id;}

    public int getSex(){ return  sex;}
    public void setSex(int sex){this.sex = sex;}

    public int getAge(){ return  this.age;}
    public void setAge(int age) { this.age= age; }

    public String getPhone(){ return  this.phone;}
    public void setPhone(String phone){ this.phone = phone;}

    public String getWeixin(){ return  this.weixin;}
    public void setWeixin(String weixin){this.weixin = weixin;}

    public String getQq(){ return  this.qq;}
    public void setQq(String qq){ this.qq = qq;}

    public int getUploadId(){ return  this.uploadId; }
    public void setUploadId(int uploadId){this.uploadId = uploadId;}

    public String getAddress(){ return this.address;}
    public void setAddress(String address){this.address = address;}

    public int getType(){ return  this.type; }
    public void setType(int type ){ this.type = type; }

    public int getLevel(){return  this.level; }
    public void setLevel(){ this.level = level; }

    public int getStatus(){ return this.status; }
    public void setStatus(int status){ this.status = status; }

    public String getName(){ return  this.name;}
    public void setName(String name){ this.name = name;}
}
