package org.example.service;

import org.example.model.Course;

import java.util.ArrayList;

public class CourseRegistrationimpl implements CourseRegistration {
    private ArrayList<Course> courseList = new ArrayList();


    // Create
    @Override
    public void addCourse(Course course){
        courseList.add(course);
    }


    // Read
    @Override
    public void displayAllCourse(){
        System.out.print(courseList);
    }

    // Update
    @Override
    public void updateCourse(Course course){
        for (int i = 0; i < courseList.size(); i++){

            if(courseList.get(i).getCourseID().equals(course.getCourseID())){
                courseList.set(i, course);
                break;
            }
        }
    }

    // Remove
    @Override
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


