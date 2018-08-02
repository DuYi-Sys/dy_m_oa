package com.duyi.oa.dao;

import com.duyi.oa.domain.QuestionBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestParam;

public interface QuestionDao {
    public HashMap<String, String> getGetUrlParams(HttpServletRequest request);
    public HashMap<String, String> getPostUrlParams(HttpServletRequest request);

    @Insert("insert into question_bank(upload_id,topic_id,reviewer_id,title,question_body, question_analysis, pic,status)\n" +
            "  values(#{uploadId},#{topicId},#{reviewerId},#{title},#{questionBody},#{questionAnalysis}, #{pic}, #{status})")
    public int insertQuestion(QuestionBody questBody);

    @Select("select id,upload_id,topic_id,title,question_body, reviewer_id, question_analysis, pic,status, reason,update_time as date from question_bank "
    +" where upload_id = #{uploadId} and status=#{status} and update_time > #{strDate} and  LOCATE(#{keyWord},question_body) > 0")
    @Results({
		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

	})
    public ArrayList<QuestionBody> selectQuestionUploadId(RowBounds brounds, @Param("uploadId")Long uploadId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select id,upload_id,topic_id,title,question_body, reviewer_id, question_analysis, pic,status, reason, update_time as date from question_bank "
            +" where upload_id = #{uploadId} and status>#{status} and update_time > #{strDate} and  LOCATE(#{keyWord},question_body) > 0")
    @Results({
		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))
	})
    public ArrayList<QuestionBody> selectQuestionUploadIdStatus(RowBounds brounds, @Param("uploadId")Long uploadId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select count(*) from question_bank where upload_id = #{uploadId} and status>#{status} and update_time > #{strDate} and  LOCATE(#{keyWord},question_body) > 0")
    public int selectQuestionUploadIdStatusCnt(@Param("uploadId")Long uploadId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select count(*) from question_bank where upload_id = #{uploadId} and status=#{status} and update_time > #{strDate} and  LOCATE(#{keyWord},question_body) > 0")
    public int getQuestionUploadIdCnt(@Param("uploadId")Long uploadId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic,status,reason,update_time as date from question_bank "
            +" where topic_id = #{topicId} and status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

	})
    public ArrayList<QuestionBody> selectQuestionTopicId(RowBounds brounds, @Param("topicId")Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic,status,reason,update_time as date from question_bank "
            +" where topic_id = #{topicId} and status > #{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

	})
    public ArrayList<QuestionBody> selectQuestionTopicIdStatus(RowBounds brounds, @Param("topicId")Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select count(*) from question_bank where topic_id = #{topicId} and status > #{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int selectQuestionTopicIdStatusCnt(@Param("topicId")Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select count(*) from question_bank where topic_id = #{topicId} and status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int getQuestionTopicIdCnt(@Param("topicId")Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status, @Param("strDate") String strDate);

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic, status, reason, update_time as date from question_bank "
            +" where upload_id = #{uploadId} and topic_id = #{topicId} and status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
 		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
 		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

 	})
    public ArrayList<QuestionBody> selectQuestionUploadTopic(RowBounds brounds, @Param("uploadId") Long uploadId, @Param("topicId") Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic, status,reason, update_time as date from question_bank "
            +" where upload_id = #{uploadId} and topic_id = #{topicId} and status>#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
 		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
 		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

 	})
    public ArrayList<QuestionBody> selectQuestionUploadTopicStatus(RowBounds brounds, @Param("uploadId") Long uploadId, @Param("topicId") Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select count(*) from question_bank where upload_id = #{uploadId} and topic_id = #{topicId} and status>#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int selectQuestionUploadTopicStatusCnt( @Param("uploadId") Long uploadId, @Param("topicId") Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select count(*) from question_bank where upload_id = #{uploadId} and topic_id = #{topicId} and status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int getQuestionUploadTopicCnt(@Param("uploadId") Long uploadId, @Param("topicId") Long topicId, @Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic, status,reason,update_time as date  from question_bank "
            +" where status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
 		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
 		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

 	})
    public ArrayList<QuestionBody> selectQuestionKeyWords(RowBounds brounds,@Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select count(*) from question_bank where status=#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int selectQuestionKeyWordsCnt(@Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select id,upload_id,topic_id,title,question_body,reviewer_id,question_analysis, pic, status,reason,update_time as date  from question_bank "
            +" where status>#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    @Results({
 		@Result(property="userInfo",column="upload_id",one=@One(select="com.duyi.admin.dao.AdminUserDao.getById")),
 		@Result(property="topicBody",column="topic_id",one=@One(select="com.duyi.oa.dao.TopicDao.getById"))

 	})
    public ArrayList<QuestionBody> selectQuestionKeyWordsStatus(RowBounds brounds,@Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Select("select count(*) from question_bank where status>#{status} and update_time > #{strDate} and LOCATE(#{keyWord},question_body) > 0")
    public int selectQuestionKeyWordsStatusCnt(@Param("keyWord") String keyWord, @Param("status")Long status,@Param("strDate") String strDate );

    @Delete("delete from question_bank where id = #{id}")
    public int deleteOperation(@RequestParam(name = "id", required = false) Long id);

    @Update("update question_bank set upload_id=#{uploadId},topic_id=#{topicId},reviewer_id=#{reviewerId},title=#{title}, "
            + " question_body=#{questionBody},question_analysis=#{questionAnalysis},pic=#{pic},status=#{status} where id = #{id}")
    public int updateOperation(QuestionBody questBody);

    @Update("update question_bank set reviewer_id=#{reviewerId},status=#{status},reason=#{reason} where id = #{id}")
    public int auditOperation(@Param("id") Long id, @Param("reviewerId") Long reviewerId,@Param("reason") String reason ,@Param("status")Long status);
}