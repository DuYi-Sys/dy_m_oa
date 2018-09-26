package com.duyi.oa.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.RequestParam;

import com.duyi.oa.domain.StudentBody;

public interface StudentDao {
    @Insert("insert into student_info(name,sex, age, phone, weixin, qq, upload_id, address, level, type, status) values(#{name},#{sex}," +
            "#{age},#{phone},#{weixin},#{qq},#{uploadId},#{address},#{level}, #{type},#{status})")
    public int insertStudentInfo(StudentBody studentBody);

    @Delete("delete from student_info where id = #{id}")
    public int deleteOperation(@RequestParam(name="id")Long id);
}