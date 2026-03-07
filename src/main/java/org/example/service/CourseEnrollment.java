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

    public void updateCourse(Course course){
        for (int i = 0; i < courseList.size(); i++){

            if(courseList.get(i).getCourseID().equals(course.getCourseID())){
                courseList.set(i, course);
                break;
            }
        }
    }

    // Remove

    public String deleteCourse(Course course){

        for(int i = 0; i < courseList.size(); i++){
            if(courseList.get(i).getCourseID().equals(course.getCourseID())) {
                courseList.remove(i);
                return "Course Successfully Deleted";
            }
        }
        return "Course ID not found";
    }


}


