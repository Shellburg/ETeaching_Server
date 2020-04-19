package com.sdp.eteaching.controller;


import com.sdp.eteaching.pojo.*;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.service.ClassService;
import com.sdp.eteaching.service.HomeworkService;
import com.sdp.eteaching.service.StudentService;
import com.sdp.eteaching.service.TeacherService;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;
    @Autowired
    StudentService studentService;
    @Autowired
    HomeworkService homeworkService;

    @RequestMapping(value = "/login.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Teacher> studentLogin(Teacher teacher, HttpSession httpSession) {
        System.out.println("login");
        ResultData<Teacher> t = teacherService.login(teacher);
        return t;
    }

    @RequestMapping(value = "/register.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Teacher> studentRegister(Teacher teacher, HttpSession httpSession) {
        System.out.println("register");
        System.out.println(teacher.getT_phonenum() + ' ' + teacher.getTeacher_password());
        ResultData<Teacher> t = teacherService.register(teacher);
        return t;
    }

    @RequestMapping(value = "/updateMessage.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Teacher> teacherUpdate(Teacher teacher, HttpSession httpSession) {
        System.out.println("updateTeacher:" + teacher.getT_phonenum());
        System.out.println(teacher.getT_phonenum());
        ResultData<Teacher> t = teacherService.updateTeacher(teacher);
        return t;
    }

//    @RequestMapping(value="/getId.do",method = {RequestMethod.POST, RequestMethod.GET})
////    public ResultData<Teacher> getId(Teacher teacher){
////        System.out.println("getTeacher:"+teacher.getT_phonenum());
////        System.out.println(teacher.getT_phonenum());
////        ResultData<Teacher> t=teacherService.
////        return t;
////    }

    @RequestMapping(value = "/createClass.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ResultData<Class> createClass(Class englishClass, HttpSession httpSession) {
        System.out.println(englishClass);
        System.out.println("Teacher:" + englishClass.getTeacher_id() + " create class");
        ResultData<Class> return_class=classService.createClass(englishClass);
        //if(newResult.getMsg()=="0"){
            //return newResult;
        //}
        //ResultData<Class> return_class = classService.findClassByT_idAndClassName(englishClass);
        //System.out.println("new class id:" + return_class.getData().getClass_id());
        return return_class;
    }

    @RequestMapping(value = "/checkAllClass.do",method = {RequestMethod.GET,RequestMethod.POST})
    public ResultData<ArrayList<Class>> checkAllClass(Teacher teacher,HttpSession httpSession){
        System.out.println("checkAllClass");
        System.out.println("Teacher:" + teacher.getTeacher_id() + " check class");
        ResultData<ArrayList<Class>> return_class =classService.findClassByT_id(teacher);
        for(int i=0;i<return_class.getData().size();i++)
        {
            System.out.println(return_class.getData().get(i).getClass_name());
        }
        return return_class;
    }

    @RequestMapping(value = "/checkAllStudents.do",method = {RequestMethod.GET,RequestMethod.POST})
    public ResultData<ArrayList<Student>> checkAllStudents(Class theClass, HttpSession httpSession){
        System.out.println("checkAllStudents");
        System.out.println("Check students info in " + theClass.getClass_id() );
        ResultData<ArrayList<Student>> return_students =studentService.findStudentsByC_id(theClass);
        for(int i=0;i<return_students.getData().size();i++)
        {
            System.out.println(return_students.getData().get(i).getStudent_id());
        }
        return return_students;
    }

    @RequestMapping(value = "/assignHomework.do",method = {RequestMethod.GET,RequestMethod.POST})
    public ResultData<Homework> assignHomework(Homework homework, HttpSession session){
        System.out.println(homework.getTeacher_id()+" assign homework in class:"+homework.getClass_id());
        homeworkService.assignHomework(homework);
        return new ResultData<Homework>();
    }

    @RequestMapping(value = "/checkAllHomework.do",method = {RequestMethod.POST})
    public ResultData<ArrayList<Homework>> checkAllHomework(Teacher teacher,HttpSession session){
        System.out.println(teacher.getTeacher_id()+" check all homework :");
        return homeworkService.checkAllHomework(teacher);
    }


}
//@RequestParam("teacher_id") Integer t_id, @RequestParam("class_id") ArrayList<Integer> classes_id,@RequestParam("homework_description") String homework_description,@RequestParam("deadline") String deadline