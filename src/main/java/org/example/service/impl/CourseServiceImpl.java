package org.example.service.impl;

import org.example.model.Course;
import org.example.service.interfaces.ICourseService;

import java.util.ArrayList;

public class CourseServiceImpl implements ICourseService {
    private ArrayList<Course> courseList = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        for (Course c : courseList) {
            if (c.getCourseID().equals(course.getCourseID())) {
                System.out.println("Error: Course ID " + course.getCourseID() + " already exists.");
                return;
            }
        }
        courseList.add(course);
        System.out.println("Course added: " + course.getCourseName());
    }

    @Override
    public void updateCourse(Course course) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(course.getCourseID())) {
                courseList.set(i, course);
                System.out.println("Course updated: " + course.getCourseName());
                return;
            }
        }
        System.out.println("Error: Course ID " + course.getCourseID() + " not found.");
    }

    @Override
    public void removeCourse(Course course) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseID().equals(course.getCourseID())) {
                courseList.remove(i);
                System.out.println("Course removed: " + course.getCourseName());
                return;
            }
        }
        System.out.println("Error: Course ID " + course.getCourseID() + " not found.");
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return courseList;
    }

    @Override
    public Course getCourseById(String courseId) {
        for (Course c : courseList) {
            if (c.getCourseID().equals(courseId)) {
                return c;
            }
        }
        return null;
    }
}