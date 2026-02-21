package org.example;

import org.example.model.Course;
import org.example.model.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


    Student s1 = new Student();
    s1.setStudentID("2024384681");
    s1.setStudentName("Justine Danielle L. Betito");
    s1.setProgram("Information Technology");



    Student s2 = new Student();
    s2.setStudentID("2024681385");
    s2.setStudentName("Paul Harris Bathan");
    s2.setProgram("Information Technology");

    Student s3 = new Student("2024384555", "John doe", "BS Nursing");



    Course c1 = new Course();
    c1.setCourseID("00001");
    c1.setCourseName("Integrative Programming and Technologies");
    c1.setProgram("Information Technology");


    Course c2 = new Course("00002", "Pharmacology", "BS Nursing");



        /*s1.display();
        System.out.println("\n\n-------------------------------------------------------------");
        s2.display();
        System.out.println("\n\n-------------------------------------------------------------");
        c1.display();*/

        // Create
        ArrayList<Student> students = new ArrayList();
        ArrayList<Course> courses = new ArrayList();

        students.add(s1);
        students.add(s2);
        students.add(s3);


        courses.add(c1);
        courses.add(c2);

        // Read

        System.out.println("READ: \n");
        System.out.println(students.get(0));
        System.out.println(students.get(1));
        System.out.println(students.get(2));
        System.out.println(students.get(2).getStudentName());

        System.out.println(courses.get(0));
        System.out.println(courses.get(1));
        System.out.println(courses.get(1).getCourseName());



        // Update
        System.out.println("\nUPDATE: \n");
        students.get(0).setStudentName("Danielle Justine Betito");
        System.out.println(students.get(0).getStudentName());

        courses.get(0).setProgram("Technology Information");
        System.out.println(courses.get(0).getCourseName());

        // Delete

        System.out.println("\nDELETE: \n");

        students.remove(1);
        courses.remove(1);

        System.out.println(students.toString());
        System.out.println(courses.toString());











    }
}




