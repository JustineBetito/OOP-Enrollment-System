package org.example.service.interfaces;

import org.example.model.Student;
import org.example.model.TuitionFeePayment;

public interface ITuitionService {
    double calculateFee(Student student, int units);
    void makePayment(TuitionFeePayment payment, double amount);
    double getRemainingBalance(TuitionFeePayment payment);
    TuitionFeePayment createTuitionRecord(Student student, double pricePerUnit);
}
