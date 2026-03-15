package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TuitionFeePaymentTest {
    private TuitionfeePayment tuitionFeePayment;

    @BeforeEach
    void setup(){
        tuitionFeePayment = new TuitionfeePayment();
    }

    @Test
    void shouldCalculateTotalTuitionFee(){
        assertEquals(5000, tuitionFeePayment.calculateTuitionFee(5, 0));
    }

    @Test
    void shouldCalculateTotalTuitionFeeWith10PercentDiscount(){
        assertEquals(4500, tuitionFeePayment.calculateTuitionFee(5, 0.10));
    }

    @Test
    void shouldMakePayment(){
        tuitionFeePayment.calculateTuitionFee(3, 0.10);
        tuitionFeePayment.makePayment(1000);
        assertEquals(1700, tuitionFeePayment.getRemainingBalance(), 0.001);
    }

    @Test
    void shouldCheckIfTuitionFeeIsNotFullyPaid(){
        tuitionFeePayment.calculateTuitionFee(3, 0.10);
        tuitionFeePayment.makePayment(1000);
        assertFalse(tuitionFeePayment.isFullyPaid());
    }

    @Test
    void shouldCheckIfTuitionFeeIsFullyPaid(){
        tuitionFeePayment.calculateTuitionFee(3, 0.10);
        tuitionFeePayment.makePayment(2700);
        assertTrue(tuitionFeePayment.isFullyPaid());
    }
}