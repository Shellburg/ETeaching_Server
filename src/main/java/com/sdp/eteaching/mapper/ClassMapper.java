package com.sdp.eteaching.mapper;

import com.sdp.eteaching.pojo.Class;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface ClassMapper {

    @Insert("insert into class(teacher_id,class_name,school_name) values (#{teacher_id},#{class_name},#{school_name})")
    void createClass(Class englishClass);

    @Select("select * from class where teacher_id=#{teacher_id}")
    ArrayList<Class> selectClassByTeacherID(int teacher_id);

    @Select("select * from class where teacher_id=#{teacher_id} and class_name=#{class_name}")
    Class selectClassByTeacherIDAndClassName(Class englishClass);

    @Select("select * from class where class_id=#{class_id}")
    Class selectClassByClassID(int class_id);

}
