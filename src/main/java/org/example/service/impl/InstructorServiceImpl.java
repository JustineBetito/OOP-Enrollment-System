package org.example.service.impl;

import org.example.model.Instructor;
import org.example.model.Section;
import org.example.service.interfaces.IInstructorService;

import java.util.ArrayList;
import java.util.HashMap;

public class InstructorServiceImpl implements IInstructorService {
    private ArrayList<Instructor> instructorList = new ArrayList<>();
    private HashMap<Integer, Section> assignments = new HashMap<>(); // instructorId -> Section

    @Override
    public void addInstructor(Instructor instructor) {
        for (Instructor i : instructorList) {
            if (i.getID() == instructor.getID()) {
                System.out.println("Error: Instructor ID " + instructor.getID() + " already exists.");
                return;
            }
        }
        instructorList.add(instructor);
        System.out.println("Instructor added: " + instructor.getName());
    }

    @Override
    public void assignInstructorToSection(Instructor instructor, Section section) {
        section.setAssignedInstructor(instructor);
        assignments.put(instructor.getID(), section);
        System.out.println("Instructor " + instructor.getName() + " assigned to section " + section.getSectionName());
    }

    @Override
    public Instructor getInstructorDetails(int instructorId) {
        for (Instructor i : instructorList) {
            if (i.getID() == instructorId) {
                return i;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Instructor> getAllInstructors() {
        return instructorList;
    }

    @Override
    public Section getInstructorAssignment(int instructorId) {
        return assignments.get(instructorId);
    }
}