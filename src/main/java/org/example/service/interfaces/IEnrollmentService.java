package org.example.service.interfaces;

import org.example.model.Department;
import org.example.model.Section;
import org.example.model.Student;
import org.example.exception.SectionFullException;

public interface IEnrollmentService {
    void enrollStudentInSection(Student student, Section section) throws SectionFullException;
    void viewDepartmentHierarchy(Department department);
    void removeStudentFromSection(Student student, Section section);
}