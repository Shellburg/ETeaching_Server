package com.sdp.eteaching.mapper;

import com.sdp.eteaching.pojo.Homework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface HomeworkMapper {
    @Insert("insert into homework(homework_id,teacher_id,student_id,class_id,homework_path,start_time,deadline,end_time,comment_path,homework_description) values (#{homework_id},#{teacher_id},#{student_id},#{class_id},#{homework_path},#{start_time},#{deadline},#{end_time},#{comment_path},#{homework_description})")
    void addHomework(Homework homework);

    @Select("select * from homework where teacher_id=#{teacher_id}")
    ArrayList<Homework> findHomeworkByTeacherID(int teacher_id);

    @Select("select * from homework where student_id=#{student_id}")
    ArrayList<Homework> findHomeworkByStudentID(int student_id);

    @Select("select * from homework where teacher_id=#{teacher_id} and  class_id=#{class_id}")
    ArrayList<Homework> findHomeworkByTeacherIAndClass(int teacher_id, int class_id);

    @Select("select * from homework where teacher_id=#{teacher_id} and  start_time={start_time}")
    ArrayList<Homework> findHomeworkByTeacherIAndStartTime(int teacher_id, String start_time);

    @Update("update homework set homework_path=#{homework_path} where student_id=#{student_id} and class_id=#{class_id}")
    void uploadAudioHomework(String homework_path,int student_id,int class_id);

}
