package com.sdp.eteaching.pojo;

import java.io.Serializable;

public class Chat_message implements Serializable {

    private String message;
    private String time;
    private String teacher_id;
    private String student_id;

    public Chat_message(String message, String time, String teacher_id, String student_id) {
        this.message = message;
        this.time = time;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
    }

    public Chat_message() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Chat_message{" +
                "message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
}
