package org.example;

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



    Course c1 = new Course();
    c1.setCourseID("00001");
    c1.setCourseName("Integrative Programming and Technologies");
    c1.setProgram("Information Technology");


       /* System.out.printf("\nStudent Name: %s\nStudent ID %s\nProgram %s", s1.getStudentName(), s1.getStudentID(), s1.getProgram());
        System.out.println("\n\n-------------------------------------------------------------");
        System.out.printf("\nStudentName: %s\nStudentID %s\nProgram %s", s2.getStudentName(), s2.getStudentID(), s2.getProgram());
        System.out.println("\n\n-------------------------------------------------------------");
        System.out.printf("\nCourse Name: %s\nCourse ID: %s\nProgram: %s", c1.getCourseName(), c1.getCourseID(), c1.getPorgram());
*/

        s1.display();
        System.out.println("\n\n-------------------------------------------------------------");
        s2.display();
        System.out.println("\n\n-------------------------------------------------------------");
        c1.display();








    }
}




