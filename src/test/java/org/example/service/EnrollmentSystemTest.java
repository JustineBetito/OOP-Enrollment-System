package org.example.service;

import org.example.exception.SectionFullException;
import org.example.model.*;
import org.example.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentSystemTest {

    private StudentServiceImpl studentService;
    private InstructorServiceImpl instructorService;
    private CourseServiceImpl courseService;
    private TuitionServiceImpl tuitionService;
    private EnrollmentServiceImpl enrollmentService;

    private Student student1;
    private Student student2;
    private Student student3;
    private Instructor instructor1;
    private Course course1;
    private Section section1;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl();
        instructorService = new InstructorServiceImpl();
        courseService = new CourseServiceImpl();
        tuitionService = new TuitionServiceImpl();
        enrollmentService = new EnrollmentServiceImpl();

        student1 = new Student(1001, "Alice Santos", "BSIT");
        student2 = new Student(1002, "Bob Reyes", "BSCS");
        student3 = new Student(1003, "Charlie Cruz", "BSIT");
        instructor1 = new Instructor(2001, "Mr. Dela Cruz", "Integrative Programming");
        course1 = new Course("C001", "Integrative Programming", "BSIT");
        section1 = new Section("S001", "BSIT-1A", 2); // max capacity of 2
    }

    // ===== TEST 1: Enroll student successfully =====
    @Test
    void testEnrollStudent_Success() throws SectionFullException {
        enrollmentService.enrollStudentInSection(student1, section1);
        assertEquals(1, section1.getEnrolledStudents().size());
        assertEquals("Alice Santos", section1.getEnrolledStudents().get(0).getName());
    }

    // ===== TEST 2: Section full - should throw SectionFullException =====
    @Test
    void testEnrollStudent_SectionFull_ThrowsException() throws SectionFullException {
        // Fill section to capacity (max = 2)
        enrollmentService.enrollStudentInSection(student1, section1);
        enrollmentService.enrollStudentInSection(student2, section1);

        // Third student should trigger SectionFullException
        assertThrows(SectionFullException.class, () -> {
            enrollmentService.enrollStudentInSection(student3, section1);
        });

        // Section size should remain at 2
        assertEquals(2, section1.getEnrolledStudents().size());
    }

    // ===== TEST 3: Tuition fee calculation =====
    @Test
    void testCalculateTuitionFee_Correct() {
        double fee = tuitionService.calculateFee(student1, 6); // 6 units x 1000
        assertEquals(6000.00, fee, 0.001);
    }

    // ===== TEST 4: Make payment and check remaining balance =====
    @Test
    void testMakePayment_ReducesBalance() {
        TuitionFeePayment payment = tuitionService.createTuitionRecord(student1, 1000.00);
        payment.setTotalTuition(tuitionService.calculateFee(student1, 6)); // 6000.00

        tuitionService.makePayment(payment, 2000.00);

        assertEquals(4000.00, tuitionService.getRemainingBalance(payment), 0.001);
        assertFalse(payment.isFullyPaid());
    }

    // ===== TEST 5: Full payment marks account as fully paid =====
    @Test
    void testMakePayment_FullPayment_MarksFullyPaid() {
        TuitionFeePayment payment = tuitionService.createTuitionRecord(student1, 1000.00);
        payment.setTotalTuition(tuitionService.calculateFee(student1, 3)); // 3000.00

        tuitionService.makePayment(payment, 3000.00);

        assertTrue(payment.isFullyPaid());
        assertEquals(0.00, tuitionService.getRemainingBalance(payment), 0.001);
    }

    // ===== TEST 6: Duplicate student ID is rejected =====
    @Test
    void testAddStudent_DuplicateId_Rejected() {
        studentService.addStudent(student1);
        studentService.addStudent(new Student(1001, "Fake Alice", "BSIT")); // same ID

        assertEquals(1, studentService.getAllStudents().size());
        assertEquals("Alice Santos", studentService.getStudentById(1001).getName());
    }

    // ===== TEST 7: Assign instructor to section =====
    @Test
    void testAssignInstructorToSection() {
        instructorService.addInstructor(instructor1);
        instructorService.assignInstructorToSection(instructor1, section1);

        assertEquals(instructor1, section1.getAssignedInstructor());
        assertEquals(section1, instructorService.getInstructorAssignment(instructor1.getID()));
    }

    // ===== TEST 8: isFull() returns true when at capacity =====
    @Test
    void testSectionIsFull_ReturnsTrueAtCapacity() throws SectionFullException {
        enrollmentService.enrollStudentInSection(student1, section1);
        enrollmentService.enrollStudentInSection(student2, section1);

        assertTrue(section1.isFull());
    }

    // ===== TEST 9: Remove student from section =====
    @Test
    void testRemoveStudentFromSection() throws SectionFullException {
        enrollmentService.enrollStudentInSection(student1, section1);
        enrollmentService.removeStudentFromSection(student1, section1);

        assertEquals(0, section1.getEnrolledStudents().size());
    }

    // ===== TEST 10: Duplicate course ID is rejected =====
    @Test
    void testAddCourse_DuplicateId_Rejected() {
        courseService.addCourse(course1);
        courseService.addCourse(new Course("C001", "Fake Course", "BSCS")); // same ID

        assertEquals(1, courseService.getAllCourses().size());
        assertEquals("Integrative Programming", courseService.getCourseById("C001").getCourseName());
    }
}
