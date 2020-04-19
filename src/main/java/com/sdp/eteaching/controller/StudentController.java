package com.sdp.eteaching.controller;

import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.pojo.Homework;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.service.ClassService;
import com.sdp.eteaching.service.StudentService;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassService classService;

    @RequestMapping(value = "/login.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Student> studentLogin(Student student, HttpSession httpSession) {
        System.out.println("login");
        ResultData<Student> s = studentService.login(student);
        return s;
    }

    @RequestMapping(value = "/register.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Student> studentRegister(Student student, HttpSession httpSession) {
        System.out.println("register");
        System.out.println(student.getS_phonenum() + ' ' + student.getStudent_password());
        ResultData<Student> s = studentService.register(student);
        return s;
    }

    @RequestMapping(value = "/updateMessage.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Student> studentUpdate(Student student, HttpSession httpSession) {
        System.out.println("updateStudent");
        System.out.println(student.getS_phonenum());
        ResultData<Student> s = studentService.updateStudent(student);
        return s;
    }

    @RequestMapping(value = "/joinInClass.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Class> joinInClass(Student student, HttpSession httpSession) {
        System.out.println(student.getStudent_id() + "join in class " + student.getClass_id());
        studentService.joinInClass(student);
        ResultData<Class> c = classService.createClass_Student(student);
        return c;
    }

    @RequestMapping(value = "/checkAllHomework.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<ArrayList<Homework>> checkAllHomework(Student student, HttpSession httpSession){
        return  studentService.findHomeworksByS_id(student);
    }

}
