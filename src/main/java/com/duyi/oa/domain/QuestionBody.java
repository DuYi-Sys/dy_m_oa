package com.duyi.oa.domain;

import java.util.Date;

public class QuestionBody {
    private int id = 0;
    private Long uploadId;
    private  Long reviewerId;
    private int topicId;
    private int status;
    private String title;
    private  String questionBody;
    private String questionAnalysis;
    private String pic;
    private Date date;
    
    public int getId(){ return this.id;}
    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() { return status; }
    public  void  setStatus(int status) {
        this.status = status;
    }

    public Long getUploadId(){ return  this.uploadId; }
    public void setUploadId(Long uploadId){ this.uploadId = uploadId; }

    public int getTopicId(){
        return  this.topicId;
    }
    public void setTopicId(int topicId){
        this.topicId = topicId;
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

    public Long getReviewerId(){ return  this.reviewerId;}
    public void setReviewerId(Long reviewerId){ this.reviewerId = reviewerId; }
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    
    
}