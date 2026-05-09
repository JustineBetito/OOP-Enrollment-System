package org.example.service;

import org.example.model.Course;
import org.example.model.Student;

public class CampusRegistrar {
    private StudentRegistration studentReg;
    private CourseRegistration courseReg;

    public CampusRegistrar(StudentRegistration studentReg, CourseRegistration courseReg){
        this.studentReg = studentReg;
        this.courseReg = courseReg;

    }

    public String addStudent(Student student){
        studentReg.addStudent(student);
        return "Success";
    }

    public String addCourse(Course course){
        courseReg.addCourse(course);
        return "Success";
    }


    public void displayAllStudent(){
        studentReg.displayAllStudent();
    }

    public void displayAllCourse(){
        courseReg.displayAllCourse();
    }


}
