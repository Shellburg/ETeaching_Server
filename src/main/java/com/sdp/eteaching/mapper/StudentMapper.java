package com.sdp.eteaching.mapper;

import com.sdp.eteaching.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface StudentMapper {
//    @Insert( "insert into student(student_id,student_password,student_name,gender,school_name,s_photo,s_phonenum) values (#{student_id},#{student_password},#{student_name},#{gender},#{school_name},#{s_photo},#{s_phonenum)")
//    void addStudent(String student_id,String student_password,String student_name,String gender,String school_name,String s_photo,String s_phonenum);

    @Insert("insert into student(student_password,student_name,gender,school_name,s_photo,s_phonenum) values (#{student_password},null,null,null,null,#{s_phonenum})")
    void addStudent(Student student);

    @Delete("delete * from student where student_id=#{student_id}")
    void deleteStudentByid(String id);

    @Update("update student set student_name=#{student_name},gender=#{gender},school_name=#{school_name},s_photo=#{s_photo} where student_id=#{student_id}")
    void updateStudent(Student student);

    @Select("select * from student where student_id=#{student_id}")
    Student selectStudentByid(int student_id);

    @Select("select * from student where s_phonenum=#{s_phonenum}")
    Student selectStudentByphonenum(String s_phonenum);

    @Select("select * from student where s_phonenum=#{s_phonenum} and student_password=#{student_password}")
    Student login(Student student);

    @Update("update student set class_id =#{class_id} where student_id=#{student_id} ")
    void joinInClass(Student student);
}
