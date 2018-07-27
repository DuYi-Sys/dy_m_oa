package com.duyi.oa.domain;

public class TopicBody {
    private Long id;
    private  String name;
    private int level;
    private int important;

    Long getId(){ return this.id; }
    void setId(Long id){ this.id =id;}

    public String getName(){ return  this.name; }
    void setName(String name) { this.name = name;}

    public int getLevel(int level){ return  this.level;}
    void setLevel(int level){ this.level = level;}

    public int getImportant(){return  important;}
    void setImportant(int important){ this.important = important;}
}
