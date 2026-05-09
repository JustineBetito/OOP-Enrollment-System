package org.example.service;

import org.example.model.Student;
import org.example.model.TuitionFeePayment;
import org.example.service.impl.TuitionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TuitionFeePaymentTest {
    private TuitionServiceImpl tuitionService;
    private TuitionFeePayment payment;
    private Student student;

    @BeforeEach
    void setup() {
        tuitionService = new TuitionServiceImpl();
        student = new Student(1001, "Alice Santos", "BSIT");
        payment = tuitionService.createTuitionRecord(student, 1000.00);
    }

    @Test
    void shouldCalculateTotalTuitionFee() {
        // 5 units x 1000.00 = 5000.00
        double fee = tuitionService.calculateFee(student, 5);
        assertEquals(5000.00, fee, 0.001);
    }

    @Test
    void shouldCalculateTotalTuitionFeeWith10PercentDiscount() {
        // 5 units x 1000.00 = 5000.00, then 10% discount = 4500.00
        double fee = tuitionService.calculateFee(student, 5);
        double discounted = fee - (fee * 0.10);
        assertEquals(4500.00, discounted, 0.001);
    }

    @Test
    void shouldMakePayment() {
        // 3 units x 1000.00 = 3000.00, pay 1000.00, balance = 2000.00
        payment.setTotalTuition(tuitionService.calculateFee(student, 3));
        tuitionService.makePayment(payment, 1000.00);
        assertEquals(2000.00, tuitionService.getRemainingBalance(payment), 0.001);
    }

    @Test
    void shouldCheckIfTuitionFeeIsNotFullyPaid() {
        // 3 units x 1000.00 = 3000.00, pay 1000.00, should NOT be fully paid
        payment.setTotalTuition(tuitionService.calculateFee(student, 3));
        tuitionService.makePayment(payment, 1000.00);
        assertFalse(payment.isFullyPaid());
    }

    @Test
    void shouldCheckIfTuitionFeeIsFullyPaid() {
        // 3 units x 1000.00 = 3000.00, pay 3000.00, should be fully paid
        payment.setTotalTuition(tuitionService.calculateFee(student, 3));
        tuitionService.makePayment(payment, 3000.00);
        assertTrue(payment.isFullyPaid());
    }
}