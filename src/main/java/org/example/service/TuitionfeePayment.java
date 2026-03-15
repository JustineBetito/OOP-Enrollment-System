package org.example.service;

public class TuitionfeePayment {
   private final double PRICE_PER_UNIT = 1000;
   private double balance;
   private double totalTuition;

   public double calculateTuitionFee(int units, double discountRate){
       totalTuition = (PRICE_PER_UNIT * units) - ((PRICE_PER_UNIT * units) * discountRate);
       balance = totalTuition;
       return totalTuition;
   }

   public void makePayment(double amount){
       if(amount >= balance){
           balance = 0;
       } else{
           balance -= amount;
       }
   }

   public double getRemainingBalance(){
       return balance;
   }

   public boolean isFullyPaid(){
       return balance == 0 ? true : false;
   }



}
