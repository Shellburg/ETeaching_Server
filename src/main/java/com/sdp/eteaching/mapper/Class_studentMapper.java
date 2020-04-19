package com.sdp.eteaching.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface Class_studentMapper {
    @Insert("insert into class_student(student_id,class_id) values(#{student_id},#{class_id}) ")
    void joinInClass(int student_id, int class_id);

    @Select("select class_id from class_student where student_id=#{student_id}")
    Integer selectClassByStudentID(int student_id);

    @Select("select student_id from class_student where class_id=#{class_id}")
    ArrayList<Integer> selectStudentsByClassID(int class_id);
}
