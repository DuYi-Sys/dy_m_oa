package com.duyi.oa.domain;

public class TopicBody {
    private Long id;
    private Long parentId;
    private  String name;
    private int level;
    private int important;

    public Long getId(){ return this.id; }
    public void setId(Long id){ this.id =id;}

    public Long getParentId(){ return this.parentId;}
    public void setParentId(Long parentId){this.parentId = parentId;}

    public String getName(){ return  this.name; }
    public void setName(String name) { this.name = name;}

    public int getLevel(int level){ return  this.level;}
    public void setLevel(int level){ this.level = level;}

    public int getImportant(){return  important;}
    public void setImportant(int important){ this.important = important;}
}