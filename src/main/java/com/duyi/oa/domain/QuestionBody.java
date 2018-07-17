package com.duyi.oa.domain;

public class QuestionBody {
    private int id = 0;
    private int uploadId;
    private  int reviewerId;
    private int topicId;
    private int status;
    private String title;
    private  String questionBody;
    private String questionAnalysis;
    private String pic;

    public int getId(){ return this.id;}
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() { return status; }
    public  void  setStatus(int status) {
        this.status = status;
    }

    public  int getUploadId(){ return  this.uploadId; }
    public void setUploadId(int uploadId){
        this.uploadId = uploadId;
    }

    public int getTopicdId(){
        return  this.topicId;
    }
    public void setTopicdId(int topicdId){
        this.topicId = topicdId;
    }

    public  String getTitle(){
        return  this.title;
    }
    public  void setTitle(String title){
        this.title = title;
    }

    public String getQuestionBody(){
        return  this.questionBody;
    }
    public void setQuestionBody(String body){
        this.questionBody = body;
    }

    public String getQuestionAnalysis(){
        return  this.questionAnalysis;
    }
    public void setQuestionAnalysis(String analysis){ this.questionAnalysis = analysis;}

    public String getPic(){ return  this.pic; }
    public  void setPic(String pic){ this.pic = pic; }

    public int getReviewerId(){ return  this.reviewerId;}
    public void setReviewerId(int reviewerId){ this.reviewerId = reviewerId; }
}