package org.example.model;

public class Instructor extends Person{

    private String course;

    public Instructor(int ID, String name, String course) {
        super(ID, name);
        this.course = course;
    }


    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course = course;
    }

}
