package org.example.service.impl;

import org.example.exception.SectionFullException;
import org.example.model.Department;
import org.example.model.Instructor;
import org.example.model.Section;
import org.example.model.Student;
import org.example.service.interfaces.IEnrollmentService;

public class EnrollmentServiceImpl implements IEnrollmentService {

    @Override
    public void enrollStudentInSection(Student student, Section section) throws SectionFullException {
        if (section.isFull()) {
            throw new SectionFullException(
                    "Enrollment failed: Section " + section.getSectionName() +
                            " is full. (Capacity: " + section.getMaxCapacity() + ")"
            );
        }
        // Prevent duplicate enrollment
        for (Student s : section.getEnrolledStudents()) {
            if (s.getID() == student.getID()) {
                System.out.println("Error: Student " + student.getName() + " is already enrolled in this section.");
                return;
            }
        }
        section.getEnrolledStudents().add(student);
        System.out.println("Success! " + student.getName() + " enrolled in section " + section.getSectionName());
    }

    @Override
    public void removeStudentFromSection(Student student, Section section) {
        boolean removed = section.getEnrolledStudents().removeIf(s -> s.getID() == student.getID());
        if (removed) {
            System.out.println(student.getName() + " removed from section " + section.getSectionName());
        } else {
            System.out.println("Error: Student " + student.getName() + " not found in section " + section.getSectionName());
        }
    }

    @Override
    public void viewDepartmentHierarchy(Department department) {
        System.out.println("\n========================================");
        System.out.println("DEPARTMENT: " + department.getDepartmentName()
                + " [ID: " + department.getDepartmentID() + "]");
        System.out.println("========================================");

        if (department.getSections().isEmpty()) {
            System.out.println("  No sections available.");
            return;
        }

        for (Section section : department.getSections()) {
            System.out.println("\n  SECTION: " + section.getSectionName()
                    + " [" + section.getCurrentEnrollmentCount() + "/" + section.getMaxCapacity() + " students]");

            Instructor instructor = section.getAssignedInstructor();
            if (instructor != null) {
                System.out.println("  Instructor: " + instructor.getName()
                        + " (Course: " + instructor.getCourse() + ")");
            } else {
                System.out.println("  Instructor: Not yet assigned");
            }

            System.out.println("  Enrolled Students:");
            if (section.getEnrolledStudents().isEmpty()) {
                System.out.println("    - No students enrolled yet.");
            } else {
                for (Student s : section.getEnrolledStudents()) {
                    System.out.println("    - [ID: " + s.getID() + "] " + s.getName()
                            + " | Program: " + s.getProgram());
                }
            }
        }
        System.out.println("========================================\n");
    }
}