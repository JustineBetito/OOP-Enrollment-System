package org.example;

import org.example.model.Instructor;
import org.example.model.Student;
import org.example.service.CampusRegistrar;
import org.example.service.StudentRegistrationimpl;
import org.example.service.CourseRegistrationimpl;
import org.example.model.Course;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Student student = new Student(2024384681, "Justine Danielle L. Betito", "BSIT");
        Instructor instructor = new Instructor(1001, "Mr. Reyes", "Integrative Programming");

        System.out.println("=== Main Task Demo ===");
        student.mainTask();
        instructor.mainTask();
        System.out.println();


        Scanner scanner = new Scanner(System.in);
        StudentRegistrationimpl studentRegistration = new StudentRegistrationimpl();
        CourseRegistrationimpl courseRegistration = new CourseRegistrationimpl();

        Student s1 = new Student(2024384681, "Justine Danielle L. Betito", "BSIT");
        Course c1 = new Course("0001", "Integrative Programming", "BSIT");

        System.out.println("=== Enrollment System ===");
        System.out.println("[1] Add Student");
        System.out.println("[2] Display Students");
        System.out.println("[3] Update Student");
        System.out.println("[4] Remove Student");
        System.out.println("[5] Add Course");
        System.out.println("[6] Display Courses");
        System.out.println("[7] Exit");


        CampusRegistrar campusRegistrar = new CampusRegistrar(studentRegistration, courseRegistration);
        while(true){
            System.out.print("Enter: ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1" -> campusRegistrar.addStudent(s1);
                case "2" -> campusRegistrar.displayAllStudent();
                case "3" -> campusRegistrar.updateStudent(s1);
                case "4" -> System.out.println(campusRegistrar.deleteStudent(s1));
                case "5" -> campusRegistrar.addCourse(c1);
                case "6" -> campusRegistrar.displayAllCourse();
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}