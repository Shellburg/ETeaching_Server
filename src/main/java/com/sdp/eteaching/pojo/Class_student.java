package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Class_student implements Serializable {

    private Integer class_id;
    private Integer student_id;

    public Class_student(Integer class_id, Integer student_id) {
        this.class_id = class_id;
        this.student_id = student_id;
    }

    public Class_student() {
    }

    public Integer getClass_id() {
        return class_id;
    }

    public void setClass_id(Integer class_id) {
        this.class_id = class_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Class_student{" +
                "class_id=" + class_id +
                ", student_id=" + student_id +
                '}';
    }
}
