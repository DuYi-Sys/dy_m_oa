package com.duyi.oa.dao;

import com.duyi.oa.domain.QuestionBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionDao {
    public HashMap<String, String> getGetUrlParams(HttpServletRequest request);
    public HashMap<String, String> getPostUrlParams(HttpServletRequest request);

    @Insert("insert into question_bank(upload_id,topic_id,reviewer_id,title,question_body, question_analysis, pic,status)\n" +
            "  values(#{uploadId},#{topicId},#{reviewerId},#{title},#{questionBody},#{questionAnalysis}, #{pic}, #{status})")
    public int insertQuestion(QuestionBody questBody);

    @Select("select id,upload_id,topic_id,title,question_body, reviewer_id, question_analysis, pic,status from question_bank "
    +" where reviewer_id = #{reviewerId} and status=#{status}")
    public ArrayList<QuestionBody> selectQuestionUploadId(@Param("reviewerId")Long reviewerId, @Param("status")Long status);

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic,status from question_bank "
            +" where topic_id = #{topicId} and status=#{status}")
    public ArrayList<QuestionBody> selectQuestionTopicId(@Param("topicId")Long topicId, @Param("status")Long status);

    @Select("select id,upload_id,topic_id,title,question_body, reviewer_id, question_analysis, pic, status from question_bank "
            +" where reviewer_id = #{reviewerId} and topic_id = #{topicId} and status=#{status}")
    public ArrayList<QuestionBody> selectQuestionUploadTopic(@Param("reviewerId") Long reviewerId, @Param("topicId") Long topicId, @Param("status")Long status);

    @Delete("delete from question_bank where id = #{id}")
    public int deleteOperation(@RequestParam(name = "id", required = false) Long id);

    @Update("update question_bank set upload_id=#{uploadId},topic_id=#{topicId},reviewer_id=#{reviewerId},title=#{title}, "
            + " question_body=#{questionBody},question_analysis=#{questionAnalysis},pic=#{pic},status=#{status} where id = #{id}")
    public int updateOperation(QuestionBody questBody);
}