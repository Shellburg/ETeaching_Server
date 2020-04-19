package com.sdp.eteaching.service;

import com.sdp.eteaching.mapper.HomeworkMapper;
import com.sdp.eteaching.pojo.Homework;
import com.sdp.eteaching.pojo.Teacher;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class HomeworkService {
    @Autowired
    HomeworkMapper homeworkMapper;
    @Autowired
    ClassService classService;

    public void assignHomework(Homework homework){
        Date now=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String startTime=dateFormat.format(now);

        ArrayList<Integer> studentsID=classService.findStudentsIDByClassID(homework.getClass_id());
        for(int i=0;i<studentsID.size();i++){
            homework.setStudent_id(studentsID.get(i));
            homework.setStart_time(startTime);
            homeworkMapper.addHomework(homework);
        }

    }

    public ResultData<ArrayList<Homework>> checkAllHomework(Teacher teacher){
        ArrayList<Homework> homeworkArrayList=new ArrayList<>();
        homeworkArrayList=homeworkMapper.findHomeworkByTeacherID(teacher.getTeacher_id());
        ResultData<ArrayList<Homework>> resultData=getResult(homeworkArrayList,1,"查询成功");
        return resultData;
    }

    public ResultData<ArrayList<Homework>> getResult(ArrayList<Homework> homework,int status,String message){
        return new ResultData<ArrayList<Homework>>(homework,status,message);
    }

}
