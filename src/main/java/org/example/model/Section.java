package org.example.model;

import java.util.ArrayList;

public class Section {
    private String sectionID;
    private String sectionName;
    private int maxCapacity;
    private ArrayList<Student> enrolledStudents;
    private Instructor assignedInstructor;

    public Section() {
        this.enrolledStudents = new ArrayList<>();
    }

    public Section(String sectionID, String sectionName, int maxCapacity) {
        this.sectionID = sectionID;
        this.sectionName = sectionName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Instructor getAssignedInstructor() {
        return assignedInstructor;
    }

    public void setAssignedInstructor(Instructor assignedInstructor) {
        this.assignedInstructor = assignedInstructor;
    }

    public int getCurrentEnrollmentCount() {
        return enrolledStudents.size();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxCapacity;
    }
}
