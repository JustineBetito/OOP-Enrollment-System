package org.example;

import org.example.model.Course;
import org.example.model.Student;
import org.example.service.CourseEnrollment;
import org.example.service.StudentEnrollment;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    StudentEnrollment studentEnrollment = new StudentEnrollment();
    CourseEnrollment courseEnrollment = new CourseEnrollment();



    // Create
        studentEnrollment.addStudent(new Student("2024384681", "Justine Danielle L. Betito", "BSIT "));
        studentEnrollment.addStudent(new Student("2024681384", "Danielle Justine Lopez", "BSIT"));

        courseEnrollment.addCourse(new Course("0001", "Inteprog", "BSIT"));
        courseEnrollment.addCourse(new Course("0002", "IT Proma", "BSIT"));


    // Display
        studentEnrollment.displayAll();
        courseEnrollment.displayAll();

    // Update
        Student updatedInfo = new Student("2024384681", "John Doe", "BSCS");
        studentEnrollment.updateStudent(updatedInfo);
        studentEnrollment.displayAll();


        Course updatedCourse = new Course("0002", "Pathfi", "BSIT");
        courseEnrollment.updateCourse(updatedCourse);
        courseEnrollment.displayAll();

    // Remove

        Student removeInfo = new Student("2024384681", "", "");
        studentEnrollment.deleteStudent(removeInfo);
        studentEnrollment.displayAll();

        Course removeCourse = new Course("0002", "", "");
        courseEnrollment.deleteCourse(removeCourse);
        courseEnrollment.displayAll();



    }
}




