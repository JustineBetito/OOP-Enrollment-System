package org.example.model;

public class Person {
    int ID;
    String name;


    public Person(int ID, String name){
        this.ID = ID;
        this.name = name;
    }

    public int getID(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


}
