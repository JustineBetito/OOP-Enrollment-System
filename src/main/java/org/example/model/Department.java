package org.example.model;

import java.util.ArrayList;

public class Department {
    private String departmentID;
    private String departmentName;
    private ArrayList<Section> sections;

    public Department() {
        this.sections = new ArrayList<>();
    }

    public Department(String departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.sections = new ArrayList<>();
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void removeSection(Section section) {
        this.sections.remove(section);
    }
}
