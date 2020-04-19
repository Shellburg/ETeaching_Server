package com.sdp.eteaching.mapper;

import com.sdp.eteaching.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper {
    @Insert("insert into teacher(teacher_password,teacher_name,gender,school_name,t_photo,t_phonenum) values (#{teacher_password},null,null,null,null,#{t_phonenum})")
    void addTeacher(Teacher teacher);

    @Delete("delete * from teacher where teacher_id=#{teacher_id}")
    void deleteTeacherByid(String teacher_id);

    @Update("update teacher set teacher_name=#{teacher_name},gender=#{gender},school_name=#{school_name},t_photo=#{t_photo} where teacher_id=#{teacher_id}")
    void updateTeacher(Teacher teacher);

    @Select("select * from teacher where teacher_id=#{teacher_id}")
    Teacher selectTeacherByid(String teacher_id);

//@Select("select * from teacher where t_phonenum=#{t_phonenum}")
//    Teacher selectTeacherByphonenum(Teacher teacher);

    @Select("select * from teacher where t_phonenum=#{t_phonenum}")
    Teacher selectTeacherByphonenum(String t_phonenum);

    @Select("select * from teacher where t_phonenum=#{t_phonenum} and teacher_password=#{teacher_password}")
    Teacher login(Teacher teacher);

}
