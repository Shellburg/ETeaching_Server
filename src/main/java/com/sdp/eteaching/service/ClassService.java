package com.sdp.eteaching.service;

import com.sdp.eteaching.mapper.ClassMapper;
import com.sdp.eteaching.mapper.Class_studentMapper;
import com.sdp.eteaching.mapper.StudentMapper;
import com.sdp.eteaching.pojo.Class;
import com.sdp.eteaching.pojo.Student;
import com.sdp.eteaching.pojo.Teacher;
import com.sdp.eteaching.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassService {
    @Autowired
    ClassMapper classMapper;
    @Autowired
    Class_studentMapper class_studentMapper;

    public ResultData<Class> createClass(Class englishClass) {
        System.out.println("Teacher:" + englishClass.getTeacher_id() + " create class in Service");
        //System.out.println(classMapper.selectClassByTeacherIDAndClassName(englishClass)!=null);
        if(classMapper.selectClassByTeacherIDAndClassName(englishClass)!=null){
            return getResult((Class) null, 0, "此班级已存在");
        }
        classMapper.createClass(englishClass);
        if (englishClass.getTeacher_id() != null) {

            Class return_class = classMapper.selectClassByTeacherIDAndClassName(englishClass);

            return getResult(return_class, 1, "添加成功");
        }

        return getResult((Class) null, 0, "添加失败");

    }


    //返回教师ID及班级名对应的单个班级
    public ResultData<Class> findClassByT_idAndClassName(Class englishClass) {
        Class return_class = classMapper.selectClassByTeacherIDAndClassName(englishClass);
        if (englishClass.getTeacher_id() != null) {

            return getResult(return_class, 1, "添加成功");
        }

        return getResult((Class) null, 0, "添加失败");
    }

    //返回教师ID对应的全部班级
    public ResultData<ArrayList<Class>> findClassByT_id(Teacher teacher) {
        ArrayList<Class> return_class = classMapper.selectClassByTeacherID(teacher.getTeacher_id());
        //return return_class;
        return getResult(return_class, 1, "查询班级成功");
    }

    //返回班级ID对应的全部学生ID
    public ArrayList<Integer> findStudentsIDByClassID(Integer classID){
        ArrayList<Integer> studentsID=class_studentMapper.selectStudentsByClassID(classID);
        return studentsID;
    }



    //建立课程——学生关系表字段及更新学生表的课程ID字段
    public ResultData<Class> createClass_Student(Student student) {
        class_studentMapper.joinInClass(student.getStudent_id(), student.getClass_id());
        Class return_class = classMapper.selectClassByClassID(student.getClass_id());

        if (student.getStudent_id() != null && student.getClass_id() != null) {

            return getResult(return_class, 1, "添加成功");
        }

        return getResult((Class) null, 0, "添加失败");
    }





    public ResultData<Class> getResult(Class englishClass, int status, String msg) {

        return new ResultData<Class>(englishClass, status, msg);

    }

    public ResultData<ArrayList<Class>> getResult(ArrayList<Class> englishClass, int status, String msg) {

        return new ResultData<ArrayList<Class>>(englishClass, status, msg);

    }

}
