package org.example.service;

import org.example.model.Student;

import java.util.ArrayList;

public class StudentEnrollment {
    private ArrayList<Student> studentsList = new ArrayList();


    // Create
    public void addStudent(Student student){
        studentsList.add(student);
    }

    // Read
    public void displayAll(){
        System.out.println(studentsList);
    }

    // Update
    public void updateStudent(Student student) {
        for (int i = 0; i < studentsList.size(); i++) {

            if (studentsList.get(i).getStudentID().equals(student.getStudentID())) {
                studentsList.set(i, student);
                break;
            }
        }

    }

    // Remove
    public String deleteStudent(Student student) {

        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getStudentID().equals(student.getStudentID())) {
                studentsList.remove(i);
                return " Student Successfully Deleted";
            }
        }
        return "Student ID not found.";
    }


    }







