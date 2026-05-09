package org.example.model;

public class TuitionFeePayment {
    private String paymentID;
    private Student student;
    private double pricePerUnit;
    private double totalTuition;
    private double balance;
    private boolean isFullyPaid;

    public TuitionFeePayment() {
        this.pricePerUnit = 1000.00; // Default price per unit
        this.isFullyPaid = false;
    }

    public TuitionFeePayment(Student student, double pricePerUnit) {
        this.student = student;
        this.pricePerUnit = pricePerUnit;
        this.totalTuition = 0.0;
        this.balance = 0.0;
        this.isFullyPaid = false;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalTuition() {
        return totalTuition;
    }

    public void setTotalTuition(double totalTuition) {
        this.totalTuition = totalTuition;
        this.balance = totalTuition;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isFullyPaid() {
        return isFullyPaid;
    }

    public void setFullyPaid(boolean fullyPaid) {
        isFullyPaid = fullyPaid;
    }
}