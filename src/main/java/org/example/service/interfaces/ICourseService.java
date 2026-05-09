package org.example.service.interfaces;

import org.example.model.Course;
import java.util.ArrayList;

public interface ICourseService {
    void addCourse(Course course);
    void updateCourse(Course course);
    void removeCourse(Course course);
    ArrayList<Course> getAllCourses();
    Course getCourseById(String courseId);
}