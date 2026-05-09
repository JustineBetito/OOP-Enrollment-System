package org.example.service.impl;

import org.example.model.Student;
import org.example.model.TuitionFeePayment;
import org.example.service.interfaces.ITuitionService;

public class TuitionServiceImpl implements ITuitionService {
    private static final double DEFAULT_PRICE_PER_UNIT = 1000.00;

    @Override
    public double calculateFee(Student student, int units) {
        return units * DEFAULT_PRICE_PER_UNIT;
    }

    @Override
    public TuitionFeePayment createTuitionRecord(Student student, double pricePerUnit) {
        TuitionFeePayment payment = new TuitionFeePayment(student, pricePerUnit);
        return payment;
    }

    @Override
    public void makePayment(TuitionFeePayment payment, double amount) {
        if (amount <= 0) {
            System.out.println("Error: Payment amount must be greater than zero.");
            return;
        }
        if (payment.isFullyPaid()) {
            System.out.println("This account is already fully paid.");
            return;
        }
        double newBalance = payment.getBalance() - amount;
        if (newBalance <= 0) {
            payment.setBalance(0);
            payment.setFullyPaid(true);
            System.out.println("Payment of " + amount + " accepted. Account is now FULLY PAID.");
        } else {
            payment.setBalance(newBalance);
            System.out.printf("Payment of %.2f accepted. Remaining balance: %.2f%n", amount, newBalance);
        }
    }

    @Override
    public double getRemainingBalance(TuitionFeePayment payment) {
        return payment.getBalance();
    }
}