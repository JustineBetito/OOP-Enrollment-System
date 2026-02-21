package org.example.service;

import org.example.model.Course;

import java.util.ArrayList;

public class CourseEnrollment {
    private ArrayList<Course> courseList = new ArrayList();


    // Create
    public void addCourse(Course course){
        courseList.add(course);
    }


    // Read
    public void displayAll(){
        System.out.print(courseList);
    }

    // Update


}
