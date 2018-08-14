package com.duyi.oa.dao;

import com.duyi.oa.domain.IntentionBody;
import com.duyi.oa.domain.QuestionBody;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface IntentionDao {
    @Insert("insert into intention(weixin,`group`,weixin_name,platform,identity,age,progress,have_train,have_open,requirements,communicate_num," +
            "acceptance,characteristics,visit_record,have_pay,upload_id,claim_id,status,ini_registration_time)"+
            " values(#{weiXin},#{group},#{weiXinName},#{platform},#{identity},#{age},#{progress},#{haveTrain},#{haveOpen},#{requirements},#{communicateNum}," +
            "#{acceptance},#{characteristics},#{visitRecord}, #{havePay}, #{uploadId}, #{claimId}, #{status}, #{iniRegistrationTime})")
    public int insertIntention(IntentionBody intentionBody);

    @Update("update intention set weixin=#{weiXin},`group`=#{group},weixin_name=#{weiXinName},platform=#{platform},identity=#{identity},age=#{age}," +
            "progress=#{progress},have_train=#{haveTrain},haveOpen=#{requirements},requirements=#{requirements},communicate_num=#{communicateNum}," +
            "acceptance=#{acceptance},characteristics=#{characteristics},visit_record=#{visitRecord},have_pay=#{havePay},upload_id=#{uploadId}," +
            "claim_id=#{claimId},status=#{status},ini_registration_time=#{iniRegistrationTime} where id=#{id}")
    public int updateIntention(IntentionBody intentionBody);

    @Delete("delete from intention where id = #{id}")
    public int deleteIntention(@Param("id") Long id);

    @Update("update intention set claim_id=#{claimId}, status=2 where id=#{id}")
    public int claimIntenion(@Param("id") Long id, @Param("claimId") Long claimId);

    @Select("select count(*) from intention where claim_id=#{claimId} and status=#{status}")
    public int countIntention(@Param("claimId") Long claimId, @Param("status") int status);

    @Select("select * from intention where  claim_id=#{claimId} and status=#{status}")
    public ArrayList<IntentionBody> selectIntention(RowBounds brounds, @Param("claimId") Long claimId, @Param("status") int status);
}
