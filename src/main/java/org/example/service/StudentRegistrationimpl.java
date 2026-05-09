package org.example.service;

import org.example.model.Student;

import java.util.ArrayList;

public class StudentRegistrationimpl implements StudentRegistration {
        private ArrayList<Student> studentsList = new ArrayList();


   @Override
    public void addStudent(Student student){
        studentsList.add(student);
    }

    @Override
    public void displayAllStudent(){
        System.out.println(studentsList);
    }

    @Override
    public void updateStudent(Student student) {
        for (int i = 0; i < studentsList.size(); i++) {

            if (studentsList.get(i).getID() == (student.getID())) {
                studentsList.set(i, student);
                break;
            }
        }

    }

    // Remove
    @Override
    public String deleteStudent(Student student) {

        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getID() == (student.getID())) {
                studentsList.remove(i);
                return " Student Successfully Deleted";
            }
        }
        return "Student ID not found.";
    }


    }







