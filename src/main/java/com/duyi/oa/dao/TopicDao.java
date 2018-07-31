package com.duyi.oa.dao;

import com.duyi.oa.domain.TopicBody;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;

public interface TopicDao {
    @Select("select id, name, level, important,parent_id from topic where level=#{level}")
    public ArrayList<TopicBody> selectTopic(RowBounds brounds, @Param("level") Long level);

    @Select("select count(*) from topic where level=#{level}")
    public int getCount(@Param("level") Long level);

    @Select("select id, name, level, important,parent_id from topic where parent_id = #{parentId}")
    public ArrayList<TopicBody> selectParentTopic(@Param("parentId") Long parentId);
}
