package org.example.service.interfaces;

import org.example.model.Instructor;
import org.example.model.Section;
import java.util.ArrayList;

public interface IInstructorService {
    void addInstructor(Instructor instructor);
    void assignInstructorToSection(Instructor instructor, Section section);
    Instructor getInstructorDetails(int instructorId);
    ArrayList<Instructor> getAllInstructors();
}