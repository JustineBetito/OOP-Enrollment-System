package org.example;

import org.example.exception.SectionFullException;
import org.example.model.*;
import org.example.service.impl.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static StudentServiceImpl studentService = new StudentServiceImpl();
    static InstructorServiceImpl instructorService = new InstructorServiceImpl();
    static CourseServiceImpl courseService = new CourseServiceImpl();
    static TuitionServiceImpl tuitionService = new TuitionServiceImpl();
    static EnrollmentServiceImpl enrollmentService = new EnrollmentServiceImpl();

    // In-memory storage for departments and sections
    static ArrayList<Department> departments = new ArrayList<>();
    static ArrayList<Section> sections = new ArrayList<>();
    static ArrayList<TuitionFeePayment> payments = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     INTERFACE-DRIVEN ENROLLMENT      ║");
        System.out.println("║            SYSTEM v2.0               ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n========= MAIN MENU =========");
            System.out.println("[1] Student Management");
            System.out.println("[2] Instructor Management");
            System.out.println("[3] Course Management");
            System.out.println("[4] Section & Department Management");
            System.out.println("[5] Enrollment");
            System.out.println("[6] Tuition Fee Management");
            System.out.println("[7] View Department Hierarchy");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> studentMenu();
                    case 2 -> instructorMenu();
                    case 3 -> courseMenu();
                    case 4 -> sectionDepartmentMenu();
                    case 5 -> enrollmentMenu();
                    case 6 -> tuitionMenu();
                    case 7 -> hierarchyMenu();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== STUDENT MENU ====================
    static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Update Student");
            System.out.println("[4] Remove Student");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Student ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Program (e.g. BSIT): ");
                        String program = scanner.nextLine().trim();
                        studentService.addStudent(new Student(id, name, program));
                    }
                    case 2 -> {
                        ArrayList<Student> list = studentService.getAllStudents();
                        if (list.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("\n--- All Students ---");
                            for (Student s : list) {
                                System.out.println("[ID: " + s.getID() + "] " + s.getName() + " | " + s.getProgram());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Student ID to update: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Student existing = studentService.getStudentById(id);
                        if (existing == null) {
                            System.out.println("Student not found.");
                        } else {
                            System.out.print("New Name (current: " + existing.getName() + "): ");
                            String name = scanner.nextLine().trim();
                            System.out.print("New Program (current: " + existing.getProgram() + "): ");
                            String program = scanner.nextLine().trim();
                            studentService.updateStudent(new Student(id, name, program));
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Student ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Student s = studentService.getStudentById(id);
                        if (s == null) {
                            System.out.println("Student not found.");
                        } else {
                            studentService.removeStudent(s);
                        }
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== INSTRUCTOR MENU ====================
    static void instructorMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Instructor Management ---");
            System.out.println("[1] Add Instructor");
            System.out.println("[2] View All Instructors");
            System.out.println("[3] Assign Instructor to Section");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Instructor ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Course handled: ");
                        String course = scanner.nextLine().trim();
                        instructorService.addInstructor(new Instructor(id, name, course));
                    }
                    case 2 -> {
                        ArrayList<Instructor> list = instructorService.getAllInstructors();
                        if (list.isEmpty()) {
                            System.out.println("No instructors found.");
                        } else {
                            System.out.println("\n--- All Instructors ---");
                            for (Instructor i : list) {
                                Section assigned = instructorService.getInstructorAssignment(i.getID());
                                String sectionInfo = (assigned != null) ? assigned.getSectionName() : "Unassigned";
                                System.out.println("[ID: " + i.getID() + "] " + i.getName()
                                        + " | Course: " + i.getCourse()
                                        + " | Section: " + sectionInfo);
                            }
                        }
                    }
                    case 3 -> {
                        if (instructorService.getAllInstructors().isEmpty()) {
                            System.out.println("No instructors available. Add one first.");
                            break;
                        }
                        if (sections.isEmpty()) {
                            System.out.println("No sections available. Add one first.");
                            break;
                        }
                        System.out.print("Enter Instructor ID: ");
                        int iId = Integer.parseInt(scanner.nextLine().trim());
                        Instructor instructor = instructorService.getInstructorDetails(iId);
                        if (instructor == null) {
                            System.out.println("Instructor not found.");
                            break;
                        }
                        System.out.println("Available Sections:");
                        for (int i = 0; i < sections.size(); i++) {
                            System.out.println("[" + i + "] " + sections.get(i).getSectionName());
                        }
                        System.out.print("Select section index: ");
                        int sIdx = Integer.parseInt(scanner.nextLine().trim());
                        if (sIdx < 0 || sIdx >= sections.size()) {
                            System.out.println("Invalid index.");
                        } else {
                            instructorService.assignInstructorToSection(instructor, sections.get(sIdx));
                        }
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== COURSE MENU ====================
    static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("[1] Add Course");
            System.out.println("[2] View All Courses");
            System.out.println("[3] Update Course");
            System.out.println("[4] Remove Course");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Course ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Course Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Program: ");
                        String program = scanner.nextLine().trim();
                        courseService.addCourse(new Course(id, name, program));
                    }
                    case 2 -> {
                        ArrayList<Course> list = courseService.getAllCourses();
                        if (list.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            System.out.println("\n--- All Courses ---");
                            for (Course c : list) {
                                System.out.println("[ID: " + c.getCourseID() + "] "
                                        + c.getCourseName() + " | Program: " + c.getProgram());
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Course ID to update: ");
                        String id = scanner.nextLine().trim();
                        Course existing = courseService.getCourseById(id);
                        if (existing == null) {
                            System.out.println("Course not found.");
                        } else {
                            System.out.print("New Course Name (current: " + existing.getCourseName() + "): ");
                            String name = scanner.nextLine().trim();
                            System.out.print("New Program (current: " + existing.getProgram() + "): ");
                            String program = scanner.nextLine().trim();
                            courseService.updateCourse(new Course(id, name, program));
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Course ID to remove: ");
                        String id = scanner.nextLine().trim();
                        Course c = courseService.getCourseById(id);
                        if (c == null) {
                            System.out.println("Course not found.");
                        } else {
                            courseService.removeCourse(c);
                        }
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== SECTION & DEPARTMENT MENU ====================
    static void sectionDepartmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Section & Department Management ---");
            System.out.println("[1] Add Department");
            System.out.println("[2] Add Section");
            System.out.println("[3] Add Section to Department");
            System.out.println("[4] View All Departments");
            System.out.println("[5] View All Sections");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Department ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Department Name: ");
                        String name = scanner.nextLine().trim();
                        departments.add(new Department(id, name));
                        System.out.println("Department added: " + name);
                    }
                    case 2 -> {
                        System.out.print("Section ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Section Name (e.g. BSIT-1A): ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Max Capacity: ");
                        int cap = Integer.parseInt(scanner.nextLine().trim());
                        sections.add(new Section(id, name, cap));
                        System.out.println("Section added: " + name + " (Capacity: " + cap + ")");
                    }
                    case 3 -> {
                        if (departments.isEmpty()) { System.out.println("No departments found."); break; }
                        if (sections.isEmpty()) { System.out.println("No sections found."); break; }
                        System.out.println("Departments:");
                        for (int i = 0; i < departments.size(); i++)
                            System.out.println("[" + i + "] " + departments.get(i).getDepartmentName());
                        System.out.print("Select department index: ");
                        int dIdx = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("Sections:");
                        for (int i = 0; i < sections.size(); i++)
                            System.out.println("[" + i + "] " + sections.get(i).getSectionName());
                        System.out.print("Select section index: ");
                        int sIdx = Integer.parseInt(scanner.nextLine().trim());
                        if (dIdx < 0 || dIdx >= departments.size() || sIdx < 0 || sIdx >= sections.size()) {
                            System.out.println("Invalid index.");
                        } else {
                            departments.get(dIdx).addSection(sections.get(sIdx));
                            System.out.println("Section added to department.");
                        }
                    }
                    case 4 -> {
                        if (departments.isEmpty()) { System.out.println("No departments found."); break; }
                        System.out.println("\n--- All Departments ---");
                        for (Department d : departments)
                            System.out.println("[ID: " + d.getDepartmentID() + "] " + d.getDepartmentName()
                                    + " | Sections: " + d.getSections().size());
                    }
                    case 5 -> {
                        if (sections.isEmpty()) { System.out.println("No sections found."); break; }
                        System.out.println("\n--- All Sections ---");
                        for (Section s : sections)
                            System.out.println("[ID: " + s.getSectionID() + "] " + s.getSectionName()
                                    + " | " + s.getCurrentEnrollmentCount() + "/" + s.getMaxCapacity() + " students");
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== ENROLLMENT MENU ====================
    static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment ---");
            System.out.println("[1] Enroll Student in Section");
            System.out.println("[2] Remove Student from Section");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        if (studentService.getAllStudents().isEmpty()) { System.out.println("No students found."); break; }
                        if (sections.isEmpty()) { System.out.println("No sections found."); break; }

                        System.out.println("Students:");
                        for (Student s : studentService.getAllStudents())
                            System.out.println("[ID: " + s.getID() + "] " + s.getName());
                        System.out.print("Enter Student ID: ");
                        int sId = Integer.parseInt(scanner.nextLine().trim());
                        Student student = studentService.getStudentById(sId);
                        if (student == null) { System.out.println("Student not found."); break; }

                        System.out.println("Sections:");
                        for (int i = 0; i < sections.size(); i++)
                            System.out.println("[" + i + "] " + sections.get(i).getSectionName()
                                    + " (" + sections.get(i).getCurrentEnrollmentCount()
                                    + "/" + sections.get(i).getMaxCapacity() + ")");
                        System.out.print("Select section index: ");
                        int sIdx = Integer.parseInt(scanner.nextLine().trim());
                        if (sIdx < 0 || sIdx >= sections.size()) { System.out.println("Invalid index."); break; }

                        try {
                            enrollmentService.enrollStudentInSection(student, sections.get(sIdx));
                        } catch (SectionFullException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        if (sections.isEmpty()) { System.out.println("No sections found."); break; }
                        System.out.println("Sections:");
                        for (int i = 0; i < sections.size(); i++)
                            System.out.println("[" + i + "] " + sections.get(i).getSectionName());
                        System.out.print("Select section index: ");
                        int sIdx = Integer.parseInt(scanner.nextLine().trim());
                        if (sIdx < 0 || sIdx >= sections.size()) { System.out.println("Invalid index."); break; }

                        Section section = sections.get(sIdx);
                        if (section.getEnrolledStudents().isEmpty()) { System.out.println("No students in this section."); break; }

                        System.out.println("Enrolled Students:");
                        for (Student s : section.getEnrolledStudents())
                            System.out.println("[ID: " + s.getID() + "] " + s.getName());
                        System.out.print("Enter Student ID to remove: ");
                        int sId = Integer.parseInt(scanner.nextLine().trim());
                        Student student = studentService.getStudentById(sId);
                        if (student == null) { System.out.println("Student not found."); break; }
                        enrollmentService.removeStudentFromSection(student, section);
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== TUITION MENU ====================
    static void tuitionMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Tuition Fee Management ---");
            System.out.println("[1] Create Tuition Record for Student");
            System.out.println("[2] Make Payment");
            System.out.println("[3] View Balance");
            System.out.println("[0] Back");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> {
                        if (studentService.getAllStudents().isEmpty()) { System.out.println("No students found."); break; }
                        System.out.println("Students:");
                        for (Student s : studentService.getAllStudents())
                            System.out.println("[ID: " + s.getID() + "] " + s.getName());
                        System.out.print("Enter Student ID: ");
                        int sId = Integer.parseInt(scanner.nextLine().trim());
                        Student student = studentService.getStudentById(sId);
                        if (student == null) { System.out.println("Student not found."); break; }

                        System.out.print("Number of units enrolled: ");
                        int units = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Price per unit (press Enter for default 1000.00): ");
                        String priceInput = scanner.nextLine().trim();
                        double price = priceInput.isEmpty() ? 1000.00 : Double.parseDouble(priceInput);

                        TuitionFeePayment payment = tuitionService.createTuitionRecord(student, price);
                        double total = tuitionService.calculateFee(student, units);
                        payment.setTotalTuition(total);

                        payments.add(payment);
                        System.out.printf("Tuition record created. Total fee: %.2f%n", total);
                    }
                    case 2 -> {
                        if (payments.isEmpty()) { System.out.println("No tuition records found."); break; }
                        System.out.println("Tuition Records:");
                        for (int i = 0; i < payments.size(); i++) {
                            TuitionFeePayment p = payments.get(i);
                            System.out.printf("[%d] %s | Balance: %.2f | %s%n",
                                    i, p.getStudent().getName(), p.getBalance(),
                                    p.isFullyPaid() ? "FULLY PAID" : "PENDING");
                        }
                        System.out.print("Select record index: ");
                        int idx = Integer.parseInt(scanner.nextLine().trim());
                        if (idx < 0 || idx >= payments.size()) { System.out.println("Invalid index."); break; }
                        System.out.print("Payment amount: ");
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        tuitionService.makePayment(payments.get(idx), amount);
                    }
                    case 3 -> {
                        if (payments.isEmpty()) { System.out.println("No tuition records found."); break; }
                        System.out.println("\n--- Tuition Records ---");
                        for (TuitionFeePayment p : payments) {
                            System.out.printf("[%s] Total: %.2f | Paid: %.2f | Balance: %.2f | %s%n",
                                    p.getStudent().getName(),
                                    p.getTotalTuition(),
                                    p.getTotalTuition() - p.getBalance(),
                                    p.getBalance(),
                                    p.isFullyPaid() ? "FULLY PAID" : "PENDING");
                        }
                    }
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // ==================== HIERARCHY MENU ====================
    static void hierarchyMenu() {
        if (departments.isEmpty()) {
            System.out.println("No departments found. Add departments first.");
            return;
        }
        System.out.println("\nDepartments:");
        for (int i = 0; i < departments.size(); i++)
            System.out.println("[" + i + "] " + departments.get(i).getDepartmentName());
        System.out.print("Select department index (or -1 to view all): ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim());
            if (idx == -1) {
                for (Department d : departments)
                    enrollmentService.viewDepartmentHierarchy(d);
            } else if (idx >= 0 && idx < departments.size()) {
                enrollmentService.viewDepartmentHierarchy(departments.get(idx));
            } else {
                System.out.println("Invalid index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}