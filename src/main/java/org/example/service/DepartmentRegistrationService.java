package org.example.service;

import org.example.model.Department;
import org.example.model.Instructor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRegistrationService implements DepartmentReg {
    List<Department> departments = new ArrayList<>();
    List<Instructor> instructorList = new ArrayList<>();


    public DepartmentRegistrationService(){
        this.departments = new ArrayList<>();
        this.instructorList = new ArrayList<>();

    }
    public void saveDepartment(String id, String department{
        departments.add(new Department(id, department, instructorList));
    }

    public List<Department> display

}
