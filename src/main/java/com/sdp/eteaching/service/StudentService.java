package com.sdp.eteaching.service;

import com.sdp.eteaching.mapper.Class_studentMapper;
import com.sdp.eteaching.mapper.HomeworkMapper;
import com.sdp.eteaching.mapper.StudentMapper;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.pojo.Homework;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    Class_studentMapper class_studentMapper;
    @Autowired
    HomeworkMapper homeworkMapper;


//    public void addStudent(String student_id,String student_password,String student_name,String gender,String school_name,String s_photo,String s_phonenum){
//        studentMapper.addStudent(student_id,student_password,student_name,gender,school_name,s_photo,s_phonenum);
//    }

    public Student selectStudentByid(int student_id) {
        return studentMapper.selectStudentByid(student_id);
    }

    public Student selectStudentByphonenum(String s_phonenum) {
        return studentMapper.selectStudentByphonenum(s_phonenum);
    }

    public ResultData<Student> login(Student student) {
        student = studentMapper.login(student);
        if (student != null) {

            System.out.println("StudentLoginSuccess:" + student.getS_phonenum() + " " + student.getStudent_password());
            return getResult(student, 1, "登录成功");

        }

        System.out.println("StudentLoginFailed");
        return getResult(null, 0, "请检查用户名和密码");

    }

    public ResultData<Student> register(Student s) {
        if(studentMapper.selectStudentByphonenum(s.getS_phonenum())!=null){
            System.out.println("StudentRegisterFailed");
            return getResult(null, 0, "此手机号已被使用");
        }
        studentMapper.addStudent(s);
        Student student = studentMapper.selectStudentByphonenum(s.getS_phonenum());
        if (student.getStudent_id() != null) {
            System.out.println("StudentRegisterSuccess:" + student.getStudent_id());
            return getResult(student, 1, "注册成功");
        }
        System.out.println("StudentRegisterFailed");
        return getResult(null, 0, "注册失败");
    }

    public ResultData<Student> updateStudent(Student student) {
        studentMapper.updateStudent(student);
        if (student.getStudent_id() != null) {
            System.out.println("UpdateStudentSuccess:" + student.getStudent_id());
            return getResult(student, 1, "更新成功");
        }
        System.out.println("UpdateStudentFailed");
        return getResult(null, 0, "更新失败");
    }

    public ResultData<Student> joinInClass(Student student) {
        System.out.println(student.getStudent_id() + "joinIn" + student.getClass_id());

        studentMapper.joinInClass(student);
        if (student.getStudent_id() != null && student.getClass_id() != null) {
            System.out.println("joinInClassSuccess:" + student.getStudent_id());
            return getResult(student, 1, "加入成功");
        }
        System.out.println("joinInClassFailed");
        return getResult(null, 0, "加入失败");
    }


    //根据课程ID在课程——学生关系表中查找学生ID再根据学生ID在学生表中查找学生
    public  ResultData<ArrayList<Student>> findStudentsByC_id(Class theClass){
        ArrayList<Integer> studentsIDList=new ArrayList<>();
        ArrayList<Student> return_students=new ArrayList<>();
        studentsIDList=class_studentMapper.selectStudentsByClassID(theClass.getClass_id());
        for(int i=0;i<studentsIDList.size();i++){
            return_students.add(studentMapper.selectStudentByid(studentsIDList.get(i)));
        }
        ResultData<ArrayList<Student>> resultData=getStudentsResult(return_students,1,"查询成功");
        return resultData;
    }

    //根据学生ID查询学生作业
    public ResultData<ArrayList<Homework>> findHomeworksByS_id(Student student){
        ArrayList<Homework> return_homeworks=new ArrayList<>();
        return_homeworks=homeworkMapper.findHomeworkByStudentID(student.getStudent_id());
        ResultData<ArrayList<Homework>> resultData=getHomeworkResult(return_homeworks,1,"查询成功");
        return resultData;
    }

    //将上传的音频文件存储位置存入数据库
    public void uploadAudioHomework(String path,int s_id,int c_id){
        homeworkMapper.uploadAudioHomework(path, s_id, c_id);
    }

    public ResultData<Student> getResult(Student user, int status, String msg) {

        return new ResultData<Student>(user, status, msg);

    }

    public ResultData<ArrayList<Student>> getStudentsResult(ArrayList<Student> students, int status, String msg) {

        return new ResultData<ArrayList<Student>>(students, status, msg);

    }

    public ResultData<ArrayList<Homework>> getHomeworkResult(ArrayList<Homework> homeworks, int status, String msg) {

        return new ResultData<ArrayList<Homework>>(homeworks, status, msg);

    }

}
