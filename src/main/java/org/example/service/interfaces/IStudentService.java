package org.example.service.interfaces;

import org.example.model.Student;
import java.util.ArrayList;

public interface IStudentService {
    void addStudent(Student student);
    void updateStudent(Student student);
    void removeStudent(Student student);
    ArrayList<Student> getAllStudents();
    Student getStudentById(int studentId);
}