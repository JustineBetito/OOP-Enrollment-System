package org.example.service;

public class Calculator {
    // add
    //subtract
    //multiply
    //divide

    public int sum;
    public int subtract;
    public int answer;

    public void sum(int a, int b ){

        answer =  a+b;
    }

    public void subtract(int a, int b){

        answer =  a-b;

    }

    public void multiply(int a , int b){

        answer = a*b;
    }

    public void divide(int a, int b){

        answer = a/b;
    }

    public int getAnswer(){
        return answer;
    }
}
