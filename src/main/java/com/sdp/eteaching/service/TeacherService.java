package com.sdp.eteaching.service;

import com.sdp.eteaching.mapper.TeacherMapper;
import com.sdp.eteaching.pojo.Teacher;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    public ResultData<Teacher> login(Teacher teacher) {
        teacher = teacherMapper.login(teacher);
        if (teacher != null) {

            System.out.println("TeacherLoginSuccess:" + teacher.getT_phonenum() + " " + teacher.getTeacher_password());
            return getResult(teacher, 1, "登录成功");

        }

        System.out.println("TeacherLoginFailed");
        return getResult(null, 0, "请检查用户名和密码");

    }

    public ResultData<Teacher> register(Teacher t) {
        if(teacherMapper.selectTeacherByphonenum(t.getT_phonenum())!=null){
            System.out.println("TeacherRegisterFailed");
            return getResult(null, 0, "此手机号已被使用");
        }
        teacherMapper.addTeacher(t);
        Teacher teacher = teacherMapper.selectTeacherByphonenum(t.getT_phonenum());
        if (teacher.getT_phonenum() != null) {
            System.out.println("TeacherRegisterSuccess:" + teacher.getT_phonenum());
            return getResult(teacher, 1, "注册成功");
        }
        System.out.println("TeacherRegisterFailed");
        return getResult(null, 0, "注册失败");
    }

    public ResultData<Teacher> updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
        if (teacher.getTeacher_id() != null) {
            System.out.println("UpdateTeacherSuccess:" + teacher.getTeacher_id());
            return getResult(teacher, 1, "更新成功");
        }
        System.out.println("UpdateTeachereFailed");
        return getResult(null, 0, "更新失败");
    }

//    public Integer getTeacherId(Teacher teacher){
//        teacherMapper.selectTeacherByphonenum(teacher);
//        if (teacher.getT_phonenum()!=null){
//            System.out.println("getTeacher:"+teacher.getT_phonenum());
//            return getResult(teacher,1,"更新成功");
//        }
//        System.out.println("UpdateTeachereFailed");
//        return getResult(null,0,"更新失败");
//    }

    public ResultData<Teacher> getResult(Teacher user, int status, String msg) {

        return new ResultData<Teacher>(user, status, msg);

    }

}
