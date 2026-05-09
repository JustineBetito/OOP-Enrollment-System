package org.example.service.impl;

import org.example.model.Student;
import org.example.service.interfaces.IStudentService;

import java.util.ArrayList;

public class StudentServiceImpl implements IStudentService {
    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        // Duplicate ID check
        for (Student s : studentList) {
            if (s.getID() == student.getID()) {
                System.out.println("Error: Student ID " + student.getID() + " already exists.");
                return;
            }
        }
        studentList.add(student);
        System.out.println("Student added: " + student.getName());
    }

    @Override
    public void updateStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getID() == student.getID()) {
                studentList.set(i, student);
                System.out.println("Student updated: " + student.getName());
                return;
            }
        }
        System.out.println("Error: Student ID " + student.getID() + " not found.");
    }

    @Override
    public void removeStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getID() == student.getID()) {
                studentList.remove(i);
                System.out.println("Student removed: " + student.getName());
                return;
            }
        }
        System.out.println("Error: Student ID " + student.getID() + " not found.");
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public Student getStudentById(int studentId) {
        for (Student s : studentList) {
            if (s.getID() == studentId) {
                return s;
            }
        }
        return null;
    }
}