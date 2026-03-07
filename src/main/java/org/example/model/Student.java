package org.example.model;

public class Student extends Person {

    private String program;

    public Student(int ID, String name, String program) {
        super(ID, name);
        this.program = program;
    }

    public String getProgram(){
        return program;
    }

    public void setProgram(String program){
        this.program = program;
    }
}


